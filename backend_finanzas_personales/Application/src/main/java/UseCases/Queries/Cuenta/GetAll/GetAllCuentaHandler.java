package UseCases.Queries.Cuenta.GetAll;

import java.util.ArrayList;
import java.util.List;

import Dto.CuentaDto;
import Entities.User;
import Entities.Cuenta.Cuenta;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;

public class GetAllCuentaHandler implements RequestHandler<GetAllCuentaQuery, List<CuentaDto>> {

    public ICuentaRepository _cuentaRepository;

    public GetAllCuentaHandler(ICuentaRepository CuentaRepository) {
        this._cuentaRepository = CuentaRepository;
    }

    @Override
    public List<CuentaDto> handle(GetAllCuentaQuery request) throws Exception {
        try {
            User.decodeTokenWithUser(request.token);
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
