package UseCases.Command.Movimiento.Create;

import Dto.MovimientoDto;
import Fourteam.mediator.Request;

public class CreateMovimientoCommand implements Request<MovimientoDto>{
    public MovimientoDto data;
    public String token;
    
    public CreateMovimientoCommand(MovimientoDto ero) {
        this.data = ero;
    }
}
