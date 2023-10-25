package UseCases.Command.Movimiento.Eliminar;

import java.util.UUID;

import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.Movimiento;
import Repositories.IMovimientoRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class EliminarMovimientoHandler implements RequestHandler<EliminarMovimientoCommand, UUID> {
    private IMovimientoRepository _movimientoRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public EliminarMovimientoHandler(IMovimientoRepository _movimientoRepository, ISecurityUtils _securityUtils,
            IUnitOfWork _unitOfWork) {
        this._movimientoRepository = _movimientoRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public UUID handle(EliminarMovimientoCommand request) throws Exception {
        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        Movimiento movimiento = _movimientoRepository.FindByKey(request.movimiento.key);
        
        UUID keyCuentaOrigen = movimiento.getKeyCuentaOrigen();
        UUID keyCuentaDestino = movimiento.getKeyCuentaDestino();
        double monto = movimiento.getMonto().getMonto();

        movimiento.eventDelete(keyCuentaOrigen, keyCuentaDestino, monto);

        _movimientoRepository.Delete(movimiento);

        _unitOfWork.commit();
        return movimiento.key;
    }

}
