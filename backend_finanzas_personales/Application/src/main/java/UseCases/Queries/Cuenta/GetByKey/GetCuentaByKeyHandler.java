package UseCases.Queries.Cuenta.GetByKey;

import Dto.CuentaDto;
import Entities.Cuenta;
import Entities.User;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.ISecurityUtils;

public class GetCuentaByKeyHandler implements RequestHandler<GetCuentaByKeyQuery, CuentaDto> {

    private ICuentaRepository _cuentaRepository;

    private ISecurityUtils _securityUtils;

    public GetCuentaByKeyHandler(ICuentaRepository cuentaRepository, ISecurityUtils securityUtils) {
        _cuentaRepository = cuentaRepository;
        _securityUtils = securityUtils;
    }

    @Override
    public CuentaDto handle(GetCuentaByKeyQuery request) throws Exception {

        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        Cuenta cuenta = _cuentaRepository.FindByKey(request.key);
        if (cuenta == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Cuenta no encontrado");
        }
        CuentaDto c = new CuentaDto(cuenta);
        return c;
    }

}
