package UseCases.Command.Cuenta.Eliminar;

import java.util.UUID;

import Dto.CuentaDto;
import Fourteam.mediator.Request;

public class EliminarCuentaCommand implements Request<CuentaDto>{
    public CuentaDto cuenta;
    public String token;

    public EliminarCuentaCommand(UUID key) {
        this.cuenta = new CuentaDto();
        this.cuenta.key = key;
    }
}
