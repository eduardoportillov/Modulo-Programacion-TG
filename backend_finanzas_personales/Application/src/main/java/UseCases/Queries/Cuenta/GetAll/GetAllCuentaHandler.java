package UseCases.Queries.Cuenta.GetAll;

import java.util.ArrayList;
import java.util.List;

import Dto.CuentaDto;
import Entities.Cuenta;
import Entities.User;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.ISecurityUtils;

public class GetAllCuentaHandler implements RequestHandler<GetAllCuentaQuery, List<CuentaDto>> {

    public ICuentaRepository _cuentaRepository;

    private ISecurityUtils _securityUtils;

    public GetAllCuentaHandler(ICuentaRepository cuentaRepository, ISecurityUtils securityUtils) {
        _cuentaRepository = cuentaRepository;
        _securityUtils = securityUtils;
    }

    @Override
    public List<CuentaDto> handle(GetAllCuentaQuery request) throws Exception {
        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        List<CuentaDto> resp = new ArrayList<>();
        for (Cuenta cuenta : _cuentaRepository.GetAll()) {
            resp.add(new CuentaDto(cuenta));
        }

        return resp;
    }

}
