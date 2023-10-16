package UseCases.Command.User.Create;

import Dto.UserDto;
import Fourteam.mediator.Request;

public class CrearUserCommand implements Request<UserDto> {
    public UserDto data;

    public CrearUserCommand(UserDto ero) {
        this.data = ero;
    }
}
