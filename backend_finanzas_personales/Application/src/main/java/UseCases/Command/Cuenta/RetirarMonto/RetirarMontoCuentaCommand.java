package UseCases.Command.Cuenta.RetirarMonto;

import Dto.CuentaDto;
import Fourteam.mediator.Request;

public class RetirarMontoCuentaCommand implements Request<CuentaDto>{
    public CuentaDto data;
    public String token;
    
    public RetirarMontoCuentaCommand(CuentaDto ero) {
        this.data = ero;
    }
}
