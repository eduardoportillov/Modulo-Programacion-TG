package UseCases.Command.User.Edit;

import java.util.UUID;

import Dto.UserDto;
import Entities.User;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class EditUserHandler implements RequestHandler<EditUserCommand, UserDto> {
    private IUserRepository _userRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public EditUserHandler(IUserRepository _userRepository, ISecurityUtils _securityUtils, IUnitOfWork _unitOfWork) {
        this._userRepository = _userRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public UserDto handle(EditUserCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        User user = _userRepository.FindByKey(keyUser);

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
