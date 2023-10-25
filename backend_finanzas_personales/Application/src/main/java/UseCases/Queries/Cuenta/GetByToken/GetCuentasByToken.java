package UseCases.Queries.Cuenta.GetByToken;

import java.util.List;
import java.util.UUID;

import Entities.Cuenta;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.ISecurityUtils;

public class GetCuentasByToken implements RequestHandler<GetCuentasByTokenQuery, List<Cuenta>> {

    public ICuentaRepository _cuentaRepository;

    private ISecurityUtils _securityUtils;

    public GetCuentasByToken(ICuentaRepository cuentaRepository, ISecurityUtils securityUtils) {
        _cuentaRepository = cuentaRepository;
        _securityUtils = securityUtils;
    }

    @Override
    public List<Cuenta> handle(GetCuentasByTokenQuery request) throws Exception {
        UUID keyUser;
        List<Cuenta> resp;

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        try {
            resp = _cuentaRepository.GetByUser(keyUser);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return resp;
    }

}
