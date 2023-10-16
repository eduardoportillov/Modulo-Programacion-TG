package UseCases.Command.User.Edit;

import java.util.UUID;

import Dto.UserDto;
import Fourteam.mediator.Request;

public class EditUserCommand implements Request<UserDto> {
    public UserDto user;
    public String token;

    public EditUserCommand(UUID key) {
        this.user = new UserDto();
        this.user.key = key;
    }
}
