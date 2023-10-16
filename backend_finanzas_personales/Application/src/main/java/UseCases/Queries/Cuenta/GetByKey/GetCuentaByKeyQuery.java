package UseCases.Queries.Cuenta.GetByKey;

import java.util.UUID;

import Fourteam.mediator.Request;

public class GetCuentaByKeyQuery implements Request<UUID> {
    public UUID key;
    public String token;

    public GetCuentaByKeyQuery(UUID key){
        this.key = key;
    }
}
