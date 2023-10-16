package UseCases.Queries.CategoriaCuenta.GetAllCategoriaCuentaUser;


import Dto.CategoriaCuentaDto;
import Fourteam.mediator.Request;

public class GetAllCategoriaCuentaUserQuery implements Request<CategoriaCuentaDto> {
    String token;
    public GetAllCategoriaCuentaUserQuery(String token) {
        this.token = token;
    }
}
