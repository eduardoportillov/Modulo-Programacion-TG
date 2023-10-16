package UseCases.Queries.Movimiento.GetByKey;

import java.util.UUID;

import Fourteam.mediator.Request;

public class GetMovimientoByKeyQuery implements Request<UUID>{
    public UUID key;
    public String token;

    public GetMovimientoByKeyQuery(UUID key){
        this.key = key;
    }
}
