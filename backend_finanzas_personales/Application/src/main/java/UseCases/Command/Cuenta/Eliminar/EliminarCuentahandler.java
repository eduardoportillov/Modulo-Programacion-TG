package UseCases.Command.Cuenta.Eliminar;

import java.util.UUID;

import Entities.User;
import Entities.Cuenta.Cuenta;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;

public class EliminarCuentahandler implements RequestHandler<EliminarCuentaCommand, UUID> {
    ICuentaRepository _cuentaRepository;

    public EliminarCuentahandler(ICuentaRepository _cuentaRepository) {
        this._cuentaRepository = _cuentaRepository;
    }

    @Override
    public UUID handle(EliminarCuentaCommand request) throws Exception {

        try {
            User.decodeTokenWithUser(request.token);
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
