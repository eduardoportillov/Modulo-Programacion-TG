package UseCases.Queries.User.FindByKey;

import Dto.UserDto;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.User;
import Repositories.ISecurityUtils;
import Repositories.IUserRepository;

public class FindUserByKeyHandler implements RequestHandler<FindUserByKeyQuery, UserDto> {

    private IUserRepository _userRepository;

    private ISecurityUtils _securityUtils;

    public FindUserByKeyHandler(IUserRepository userRepository, ISecurityUtils securityUtils) {
        _userRepository = userRepository;
        _securityUtils = securityUtils;
    }

    @Override
    public UserDto handle(FindUserByKeyQuery request) throws Exception {

        try {
            _securityUtils.decodeToken(request.token);
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
