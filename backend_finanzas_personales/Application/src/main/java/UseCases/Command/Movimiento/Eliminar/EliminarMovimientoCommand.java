package UseCases.Command.Movimiento.Eliminar;

import java.util.UUID;

import Dto.MovimientoDto;
import Fourteam.mediator.Request;

public class EliminarMovimientoCommand implements Request<MovimientoDto>{
    public MovimientoDto movimiento;
    public String token;

    public EliminarMovimientoCommand(UUID key) {
        this.movimiento = new MovimientoDto();
        this.movimiento.key = key;
    }
}
