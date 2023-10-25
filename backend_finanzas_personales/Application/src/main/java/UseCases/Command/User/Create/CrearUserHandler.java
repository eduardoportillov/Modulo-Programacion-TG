package UseCases.Command.User.Create;

import Entities.User;
import Factories.User.IUserFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class CrearUserHandler implements RequestHandler<CrearUserCommand, String> {
  private IUserFactory _userFactory;
  private IUserRepository _userRepository;

  private ISecurityUtils _securityUtils;

  private IUnitOfWork _unitOfWork;



  public CrearUserHandler(IUserFactory _userFactory, IUserRepository _userRepository, ISecurityUtils _securityUtils,
      IUnitOfWork _unitOfWork) {
    this._userFactory = _userFactory;
    this._userRepository = _userRepository;
    this._securityUtils = _securityUtils;
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

    return _securityUtils.generateToken(user.getKey());
  }
}
