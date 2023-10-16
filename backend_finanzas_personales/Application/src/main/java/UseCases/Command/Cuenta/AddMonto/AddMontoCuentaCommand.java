package UseCases.Command.Cuenta.AddMonto;

import Dto.CuentaDto;
import Fourteam.mediator.Request;

public class AddMontoCuentaCommand implements Request<CuentaDto>{
    public CuentaDto data;
    public String token;
    
    public AddMontoCuentaCommand(CuentaDto ero) {
        this.data = ero;
    }
}
