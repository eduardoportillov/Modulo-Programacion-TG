package UseCases.Queries.CategoriaMovimiento.GetAllCategoriaMoviminetoUser;


import Dto.CategoriaCuentaDto;
import Fourteam.mediator.Request;

public class GetAllCategoriaMoviminetoUserQuery implements Request<CategoriaCuentaDto> {
    String token;
    public GetAllCategoriaMoviminetoUserQuery(String token) {
        this.token = token;
    }
}
