package UseCases.Command.Cuenta.Edit;

import java.util.UUID;

import Dto.CuentaDto;
import Fourteam.mediator.Request;

public class EditCuentaCommand implements Request<CuentaDto>{
    public CuentaDto cuenta;
    public String token;

    public EditCuentaCommand(UUID key) {
        this.cuenta = new CuentaDto();
        this.cuenta.key = key;
        
    }
}
