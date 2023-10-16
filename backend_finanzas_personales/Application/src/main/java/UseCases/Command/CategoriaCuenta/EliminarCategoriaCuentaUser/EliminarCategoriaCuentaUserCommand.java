package UseCases.Command.CategoriaCuenta.EliminarCategoriaCuentaUser;

import java.util.UUID;

import Dto.CategoriaCuentaDto;
import Dto.CuentaDto;
import Fourteam.mediator.Request;

public class EliminarCategoriaCuentaUserCommand implements Request<CategoriaCuentaDto> {
    public CategoriaCuentaDto categoriacuenta;
    public String token;

    public EliminarCategoriaCuentaUserCommand(UUID key) {
        this.categoriacuenta = new CategoriaCuentaDto();
        this.categoriacuenta.key = key;
    }
}