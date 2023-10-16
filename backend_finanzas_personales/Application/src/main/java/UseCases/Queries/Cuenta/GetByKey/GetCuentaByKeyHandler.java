package UseCases.Queries.Cuenta.GetByKey;

import Dto.CuentaDto;
import Entities.User;
import Entities.Cuenta.Cuenta;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;

public class GetCuentaByKeyHandler implements RequestHandler<GetCuentaByKeyQuery, CuentaDto> {

    private ICuentaRepository _cuentaRepository;

    public GetCuentaByKeyHandler(ICuentaRepository userRepository) {
        this._cuentaRepository = userRepository;
    }

    @Override
    public CuentaDto handle(GetCuentaByKeyQuery request) throws Exception {

        try {
            User.decodeTokenWithUser(request.token);
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
