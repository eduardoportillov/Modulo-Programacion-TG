package UseCases.Command.CategoriaCuenta.EditCategoriaCuentaUser;

import java.util.UUID;

import Dto.CategoriaCuentaDto;
import Fourteam.mediator.Request;

public class EditCategoriaCuentaUserCommand implements Request<UUID>{
    public CategoriaCuentaDto categoriacuenta;
    public String token;
    
    public EditCategoriaCuentaUserCommand(UUID key){
        this.categoriacuenta = new CategoriaCuentaDto();
        this.categoriacuenta.key = key;
    }
    
}
