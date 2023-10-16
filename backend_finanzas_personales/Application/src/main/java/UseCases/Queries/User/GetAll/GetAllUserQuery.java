package UseCases.Queries.User.GetAll;

import java.util.UUID;

import Dto.UserDto;
import Fourteam.mediator.Request;

public class GetAllUserQuery implements Request<UserDto> {
    public String token;

    public GetAllUserQuery(String auth) {
        this.token = auth;
    }
}
