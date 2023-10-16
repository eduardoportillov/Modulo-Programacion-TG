package UseCases.Command.User.Edit;

import Dto.UserDto;
import Entities.User;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class EditUserHandler implements RequestHandler<EditUserCommand, UserDto> {
    private IUserRepository _userRepository;
    private IUnitOfWork _unitOfWork;

    public EditUserHandler(IUserRepository _userRepository, IUnitOfWork _unitOfWork) {
        this._userRepository = _userRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public UserDto handle(EditUserCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        if (request.user.email != null) {
            user.email = request.user.email;
        }

        if (request.user.password != null) {
            String hashedPassword = BCrypt.withDefaults().hashToString(12, request.user.password.toCharArray());
            user.password = hashedPassword;
        }

        _userRepository.Update(user);
        _unitOfWork.commit();

        return new UserDto(user);
    }

}
