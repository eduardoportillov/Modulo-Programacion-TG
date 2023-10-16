package UseCases.Queries.User.FindByKey;

import Dto.UserDto;
import Entities.User;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUserRepository;

public class FindUserByKeyHandler implements RequestHandler<FindUserByKeyQuery, UserDto> {

    private IUserRepository _userRepository;

    public FindUserByKeyHandler(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public UserDto handle(FindUserByKeyQuery request) throws Exception {

        try {
            User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }
        
        User user = _userRepository.FindByKey(request.key);
        if (user == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Usuario no encontrado");
        }
        UserDto userDto = new UserDto(user);
        return userDto;
    }

}
