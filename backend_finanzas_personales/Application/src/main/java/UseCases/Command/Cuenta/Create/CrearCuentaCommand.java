package UseCases.Command.Cuenta.Create;

import Dto.CuentaDto;
import Fourteam.mediator.Request;

public class CrearCuentaCommand implements Request<CuentaDto>{
    public CuentaDto data;
    public String token;
    
    public CrearCuentaCommand(CuentaDto ero) {
        this.data = ero;
    }
}
