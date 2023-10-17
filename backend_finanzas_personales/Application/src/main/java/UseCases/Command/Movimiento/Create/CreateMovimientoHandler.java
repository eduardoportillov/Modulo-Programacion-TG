package UseCases.Command.Movimiento.Create;

import java.time.LocalDateTime;
import Entities.User;
import Entities.Cuenta.Cuenta;
import Entities.Movimiento.Movimiento;
import Factories.Movimiento.IMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.IMovimientoRepository;
import Repositories.IUnitOfWork;

public class CreateMovimientoHandler implements RequestHandler<CreateMovimientoCommand, String> {
    private IMovimientoFactory _movimientoFactory;
    private IMovimientoRepository _movimientoRepository;

    private ICuentaRepository _cuentaRepository;
    private IUnitOfWork _unitOfWork;

    public CreateMovimientoHandler(IMovimientoFactory _movimientoFactory, IMovimientoRepository _movimientoRepository,
            ICuentaRepository _cuentaRepository, IUnitOfWork _unitOfWork) {
        this._movimientoFactory = _movimientoFactory;
        this._movimientoRepository = _movimientoRepository;
        this._cuentaRepository = _cuentaRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(CreateMovimientoCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        Cuenta cuentaOrigen = _cuentaRepository.FindByKey(request.data.keyCuentaOrigen);

        if (cuentaOrigen == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La cuenta origen no existe");
        }

        if (cuentaOrigen.getMonto().getMonto() < request.data.monto) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La cuenta origen no tiene saldo suficiente");
        }

        if (user.isCategoriaMovimientoUser(request.data.keyCategoria)) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría movimiento no pertenece al usuario");
        }

        Cuenta cuentaDestino = _cuentaRepository.FindByKey(request.data.keyCuentaDestino);

        if (cuentaDestino == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La cuenta destino no existe");
        }

        cuentaOrigen.restarMonto(request.data.monto);
        cuentaDestino.addMonto(request.data.monto);

        Movimiento movimiento = _movimientoFactory.Create(
                request.data.keyCuentaOrigen,
                request.data.keyCuentaDestino,
                request.data.descripcion,
                request.data.keyCategoria,
                request.data.monto,
                LocalDateTime.now());

        _movimientoRepository.Create(movimiento);

        _cuentaRepository.Update(cuentaOrigen);
        _cuentaRepository.Update(cuentaDestino);
        _unitOfWork.commit();

        return "Movimiento Creado con exito ";
    }

}
