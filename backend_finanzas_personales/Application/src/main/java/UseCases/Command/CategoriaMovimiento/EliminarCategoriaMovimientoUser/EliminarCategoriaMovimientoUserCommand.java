package UseCases.Command.CategoriaMovimiento.EliminarCategoriaMovimientoUser;

import java.util.UUID;

import Dto.CategoriaMovimientoDto;
import Fourteam.mediator.Request;

public class EliminarCategoriaMovimientoUserCommand implements Request<CategoriaMovimientoDto> {
    public CategoriaMovimientoDto categoriaMovimiento;
    public String token;

    public EliminarCategoriaMovimientoUserCommand(UUID key) {
        this.categoriaMovimiento = new CategoriaMovimientoDto();
        this.categoriaMovimiento.key = key;
    }
}