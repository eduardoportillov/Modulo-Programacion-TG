package UseCases.Queries.Cuenta.GetAll;


import Dto.CuentaDto;
import Fourteam.mediator.Request;

public class GetAllCuentaQuery implements Request<CuentaDto> {
    String token;
    public GetAllCuentaQuery(String token) {
        this.token = token;
    }
}
