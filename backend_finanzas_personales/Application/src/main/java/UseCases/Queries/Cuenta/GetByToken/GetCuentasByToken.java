package UseCases.Queries.Cuenta.GetByToken;

import java.util.List;

import Entities.User;
import Entities.Cuenta.Cuenta;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;

public class GetCuentasByToken implements RequestHandler<GetCuentasByTokenQuery, List<Cuenta>> {

    public ICuentaRepository _cuentaRepository;

    public GetCuentasByToken(ICuentaRepository cuentaRepository) {
        this._cuentaRepository = cuentaRepository;
    }

    @Override
    public List<Cuenta> handle(GetCuentasByTokenQuery request) throws Exception {
        User user;
        List<Cuenta> resp;

        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        try {
            resp = _cuentaRepository.GetByUser(user.key);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return resp;
    }

}
