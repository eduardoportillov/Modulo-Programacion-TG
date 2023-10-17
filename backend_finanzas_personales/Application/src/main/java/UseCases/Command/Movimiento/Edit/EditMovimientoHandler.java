package UseCases.Command.Movimiento.Edit;

import Entities.User;
import Entities.Cuenta.Cuenta;
import Entities.Movimiento.Movimiento;
import Entities.ValueObject.Monto;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.IMovimientoRepository;
import Repositories.IUnitOfWork;

public class EditMovimientoHandler implements RequestHandler<EditMovimientoCommand, String> {
    private IMovimientoRepository _movimientoRepository;

    private ICuentaRepository _cuentaRepository;
    private IUnitOfWork _unitOfWork;

    public EditMovimientoHandler(IMovimientoRepository _movimientoRepository,
            ICuentaRepository _cuentaRepository, IUnitOfWork _unitOfWork) {
        this._movimientoRepository = _movimientoRepository;
        this._cuentaRepository = _cuentaRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EditMovimientoCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        Movimiento movimiento = _movimientoRepository.FindByKey(request.movimiento.key);
        if (movimiento == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "El movimiento no existe");
        }

        if (request.movimiento.keyCategoria != null) {
            if (user.isCategoriaMovimientoUser(request.movimiento.keyCategoria)) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría movimiento no pertenece al usuario");
            }
            movimiento.KeyCategoria = request.movimiento.keyCategoria;
        }

        if (request.movimiento.descripcion != null) {
            movimiento.descripcion = request.movimiento.descripcion;
        }

        Cuenta cuentaOrigen = _cuentaRepository.FindByKey(movimiento.keyCuentaOrigen);
        Cuenta cuentaDestino = _cuentaRepository.FindByKey(movimiento.keyCuentaDestino);
        double diferencia;

        if (request.movimiento.monto > movimiento.getMonto().getMonto()) {
            diferencia = request.movimiento.monto - movimiento.getMonto().getMonto();
            if (cuentaOrigen.getMonto().getMonto() < diferencia) {
                throw new HttpException(HttpStatus.BAD_REQUEST,
                        "El monto a transferir es mayor al monto de la cuenta origen");
            }
            cuentaOrigen.getMonto().restar(diferencia);
            cuentaDestino.getMonto().sumar(diferencia);
        }

        if (request.movimiento.monto < movimiento.getMonto().getMonto()) {
            diferencia =  movimiento.getMonto().getMonto() - request.movimiento.monto ;
            if (cuentaDestino.getMonto().getMonto() < diferencia) {
                throw new HttpException(HttpStatus.BAD_REQUEST,
                        "El monto a transferir es mayor al monto de la cuenta destino");
            }
            cuentaOrigen.getMonto().sumar(diferencia);
            cuentaDestino.getMonto().restar(diferencia);
        }

        movimiento.monto = new Monto(request.movimiento.monto);

        _cuentaRepository.Update(cuentaOrigen);
        _cuentaRepository.Update(cuentaDestino);

        _movimientoRepository.Update(movimiento);
        _unitOfWork.commit();

        return "Movimiento editado con exito";
    }

}
