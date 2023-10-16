package UseCases.Command.CategoriaCuenta.Create;

import Dto.CategoriaCuentaDto;
import Fourteam.mediator.Request;

public class CrearCategoriaCuentaCommand implements Request<CategoriaCuentaDto>{
    public CategoriaCuentaDto data;
    public String token;

    public CrearCategoriaCuentaCommand(CategoriaCuentaDto ero) {
        this.data = ero;
    }
}
