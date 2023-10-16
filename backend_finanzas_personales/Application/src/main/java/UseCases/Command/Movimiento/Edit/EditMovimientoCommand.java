package UseCases.Command.Movimiento.Edit;

import java.util.UUID;

import Dto.MovimientoDto;
import Fourteam.mediator.Request;

public class EditMovimientoCommand implements Request<MovimientoDto> {
    public MovimientoDto movimiento;
    public String token;

    public EditMovimientoCommand(UUID key) {
        this.movimiento = new MovimientoDto();
        this.movimiento.key = key;
    }
}
