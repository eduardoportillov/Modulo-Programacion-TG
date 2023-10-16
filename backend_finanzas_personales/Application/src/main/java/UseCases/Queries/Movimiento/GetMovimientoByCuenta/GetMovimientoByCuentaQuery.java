package UseCases.Queries.Movimiento.GetMovimientoByCuenta;

import java.util.UUID;

import Fourteam.mediator.Request;

public class GetMovimientoByCuentaQuery implements Request<UUID>{
    public UUID keyCuenta;
    public String token;

    public GetMovimientoByCuentaQuery(UUID keyCuenta){
        this.keyCuenta = keyCuenta;
    }
}
