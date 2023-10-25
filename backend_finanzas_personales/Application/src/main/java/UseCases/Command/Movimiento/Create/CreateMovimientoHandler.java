package UseCases.Command.Movimiento.Create;

import java.time.LocalDateTime;
import java.util.UUID;

import Entities.Movimiento;
import Factories.Movimiento.IMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.IMovimientoRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class CreateMovimientoHandler implements RequestHandler<CreateMovimientoCommand, String> {
    private IMovimientoFactory _movimientoFactory;
    private IMovimientoRepository _movimientoRepository;

    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public CreateMovimientoHandler(IMovimientoFactory _movimientoFactory, IMovimientoRepository _movimientoRepository,
            ICategoriaMovimientoRepository _categoriaMovimientoRepository, ISecurityUtils _securityUtils,
            IUnitOfWork _unitOfWork) {
        this._movimientoFactory = _movimientoFactory;
        this._movimientoRepository = _movimientoRepository;
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(CreateMovimientoCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        if (_categoriaMovimientoRepository.FindByKey(request.data.keyCategoria) == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría movimiento no pertenece al usuario"); // TODO Revisar esta validación
        }

        Movimiento movimiento = _movimientoFactory.Create(
                request.data.keyCuentaOrigen,
                request.data.keyCuentaDestino,
                request.data.descripcion,
                request.data.keyCategoria,
                request.data.monto,
                LocalDateTime.now());

        movimiento.evenCreado(keyUser, request.data.keyCuentaOrigen, request.data.keyCuentaDestino,
                request.data.monto);

        _movimientoRepository.Create(movimiento);
        _unitOfWork.commit();

        return "Movimiento Creado con exito ";
    }

}
