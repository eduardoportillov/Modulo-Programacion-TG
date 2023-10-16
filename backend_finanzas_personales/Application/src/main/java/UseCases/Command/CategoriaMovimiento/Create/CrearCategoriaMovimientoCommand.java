package UseCases.Command.CategoriaMovimiento.Create;

import Dto.CategoriaMovimientoDto;
import Fourteam.mediator.Request;

public class CrearCategoriaMovimientoCommand implements Request<CategoriaMovimientoDto>{
    public CategoriaMovimientoDto data;
    public String token;

    public CrearCategoriaMovimientoCommand(CategoriaMovimientoDto ero) {
        this.data = ero;
    }
}
