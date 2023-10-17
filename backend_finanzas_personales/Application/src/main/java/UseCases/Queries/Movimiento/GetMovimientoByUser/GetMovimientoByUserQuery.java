package UseCases.Queries.Movimiento.GetMovimientoByUser;

import java.util.UUID;

import Fourteam.mediator.Request;

public class GetMovimientoByUserQuery implements Request<UUID>{
    public UUID keyUser;
    public String token;

    public GetMovimientoByUserQuery(UUID keyUser){
        this.keyUser = keyUser;
    }
}
