package UseCases.Command.Cuenta.Eliminar;

import java.util.UUID;

import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.Cuenta;
import Repositories.ICuentaRepository;
import Repositories.ISecurityUtils;

public class EliminarCuentahandler implements RequestHandler<EliminarCuentaCommand, UUID> {
    ICuentaRepository _cuentaRepository;

    private ISecurityUtils _securityUtils;

    public EliminarCuentahandler(ICuentaRepository _cuentaRepository, ISecurityUtils _securityUtils) {
        this._cuentaRepository = _cuentaRepository;
        this._securityUtils = _securityUtils;
    }

    @Override
    public UUID handle(EliminarCuentaCommand request) throws Exception {

        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        Cuenta cuenta = _cuentaRepository.FindByKey(request.cuenta.key);
        if (cuenta == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Cuenta no encontrada");
        }

        return _cuentaRepository.Delete(cuenta).key;
    }

}
