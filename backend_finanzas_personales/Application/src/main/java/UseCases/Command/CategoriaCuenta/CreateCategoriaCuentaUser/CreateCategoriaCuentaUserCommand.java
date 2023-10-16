package UseCases.Command.CategoriaCuenta.CreateCategoriaCuentaUser;

import Dto.CategoriaCuentaDto;
import Fourteam.mediator.Request;

public class CreateCategoriaCuentaUserCommand implements Request<CategoriaCuentaDto> {
    public CategoriaCuentaDto cc;
    public String token;

    public CreateCategoriaCuentaUserCommand(CategoriaCuentaDto cc) {
        this.cc = cc;
    }
}
