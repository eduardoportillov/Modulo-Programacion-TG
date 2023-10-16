package UseCases.Queries.User.Login;


import Dto.UserDto;
import Fourteam.mediator.Request;

public class LoginUserQuery implements Request<UserDto>{
    public String email;
    public String password;

    public LoginUserQuery(UserDto userDto){
        this.email = userDto.email;
        this.password = userDto.password;
    }
}
