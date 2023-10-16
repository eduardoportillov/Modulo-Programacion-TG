package UseCases.Command.CategoriaMovimiento.CreateCategoriaMovimientoUser;

import Dto.CategoriaMovimientoDto;
import Fourteam.mediator.Request;

public class CreateCategoriaMovimientoUserCommand implements Request<CategoriaMovimientoDto> {
    public CategoriaMovimientoDto cm;
    public String token;

    public CreateCategoriaMovimientoUserCommand(CategoriaMovimientoDto cm) {
        this.cm = cm;
    }
}
