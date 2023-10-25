package UseCases.Queries.User.GetAll;

import java.util.ArrayList;
import java.util.List;

import Dto.UserDto;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.User;
import Repositories.ISecurityUtils;
import Repositories.IUserRepository;

public class GetAllUserHandler implements RequestHandler<GetAllUserQuery, List<UserDto>> {

    public IUserRepository _userRepository;

    private ISecurityUtils _securityUtils;

    public GetAllUserHandler(IUserRepository userRepository, ISecurityUtils securityUtils) {
        _userRepository = userRepository;
        _securityUtils = securityUtils;
    }

    @Override
    public List<UserDto> handle(GetAllUserQuery request) throws Exception {

        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        List<UserDto> resp = new ArrayList<>();
        for (User us : _userRepository.GetAll()) {
            resp.add(new UserDto(us));
        }
        return resp;
    }

}
