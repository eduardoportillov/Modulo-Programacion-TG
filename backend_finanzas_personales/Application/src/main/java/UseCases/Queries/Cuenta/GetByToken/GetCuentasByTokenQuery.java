package UseCases.Queries.Cuenta.GetByToken;

import Fourteam.mediator.Request;

public class GetCuentasByTokenQuery implements Request<String> {
    public String token;

    public GetCuentasByTokenQuery(String auth) {
        this.token = auth;
    }
}
