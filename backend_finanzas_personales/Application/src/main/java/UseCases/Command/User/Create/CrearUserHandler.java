package UseCases.Command.User.Create;

import Entities.User;
import Factories.User.IUserFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class CrearUserHandler implements RequestHandler<CrearUserCommand, String> {
  private IUserFactory _userFactory;
  private IUserRepository _userRepository;
  private IUnitOfWork _unitOfWork;

  public CrearUserHandler(
      IUserFactory userFactory,
      IUserRepository userRepository,
      IUnitOfWork _unitOfWork) {
    this._userFactory = userFactory;
    this._userRepository = userRepository;
    this._unitOfWork = _unitOfWork;
  }

  @Override
  public String handle(CrearUserCommand request) throws Exception {
    User user = _userRepository.FindByEmail(request.data.email);
    if (user != null) {
      throw new HttpException(HttpStatus.BAD_REQUEST, "Ya existe ese usuario ya que el correo esta siendo usado.");
    }

    if (request.data.password == null || request.data.password.length() <= 0) {
      throw new HttpException(HttpStatus.BAD_REQUEST, "El password no puede estar vacio.");
    }

    String hashedPassword = BCrypt.withDefaults().hashToString(12, request.data.password.toCharArray());

    user = _userFactory.Create(request.data.email, hashedPassword);
    
    user.eventCreado(user.key);

    _userRepository.Create(user);

    _unitOfWork.commit();

    return user.generateTokenWithUser();
  }
}
