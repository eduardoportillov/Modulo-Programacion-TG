package UseCases.Command.CategoriaMovimiento.EditCategoriaMovimientoUser;

import java.util.UUID;

import Dto.CategoriaMovimientoDto;
import Fourteam.mediator.Request;

public class EditCategoriaMovimientoUserCommand implements Request<UUID>{
    public CategoriaMovimientoDto categoriaMovimiento;
    public String token;
    
    public EditCategoriaMovimientoUserCommand(UUID key){
        this.categoriaMovimiento = new CategoriaMovimientoDto();
        this.categoriaMovimiento.key = key;
    }
    
}
