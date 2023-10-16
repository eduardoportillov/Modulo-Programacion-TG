package UseCases.Queries.User.Login;

import Entities.User;
import Repositories.IUserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;

public class LoginUserHandler implements RequestHandler<LoginUserQuery, String> {

    private IUserRepository _userRepository;

    public LoginUserHandler(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public String handle(LoginUserQuery request) throws Exception {

        if (request.email == null || request.email.length() <= 0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "El email es requerido.");
        }
        if (request.password == null || request.password.length() <= 0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "El password no puede es requerido.");
        }

        User user = _userRepository.FindByEmail(request.email);

        if (user == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Usuario no encontrado.");
        }

        String hashedPassword = user.password;

        BCrypt.Result result = BCrypt.verifyer().verify(request.password.toCharArray(), hashedPassword);

        if (!result.verified) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "password invalid.");
        }

        return user.generateTokenWithUser();
    }
}
