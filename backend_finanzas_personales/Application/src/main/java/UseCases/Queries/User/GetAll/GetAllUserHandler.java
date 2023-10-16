package UseCases.Queries.User.GetAll;

import java.util.ArrayList;
import java.util.List;

import Dto.UserDto;
import Entities.User;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUserRepository;

public class GetAllUserHandler implements RequestHandler<GetAllUserQuery, List<UserDto>> {

    public IUserRepository _userRepository;

    public GetAllUserHandler(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public List<UserDto> handle(GetAllUserQuery request) throws Exception {

        try {
            User.decodeTokenWithUser(request.token);
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
