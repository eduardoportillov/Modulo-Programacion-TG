package UseCases.Command.CategoriaCuenta.CreateCategoriaCuentaUser;

import Dto.CategoriaCuentaDto;
import Entities.User;
import Entities.Cuenta.CategoriaCuenta;
import Factories.CategoriaCuenta.ICategoriaCuentaFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;

public class CreateCategoriaCuentaUserHandler
        implements RequestHandler<CreateCategoriaCuentaUserCommand, CategoriaCuentaDto> {
    private IUserRepository _userRepository;
    private ICategoriaCuentaFactory _cuentaCategoriaFactory;
    private IUnitOfWork _unitOfWork;

    public CreateCategoriaCuentaUserHandler(IUserRepository _userRepository,
            ICategoriaCuentaFactory _cuentaCategoriaFactory, IUnitOfWork _unitOfWork) {
        this._userRepository = _userRepository;
        this._cuentaCategoriaFactory = _cuentaCategoriaFactory;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public CategoriaCuentaDto handle(CreateCategoriaCuentaUserCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
            user = _userRepository.FindByKey(user.key);
            CategoriaCuenta categoriaCuenta = _cuentaCategoriaFactory.Create(request.cc.getNombre());

            user.addCategoriaCuentaUser(categoriaCuenta);

            _userRepository.Update(user);
            _unitOfWork.commit();

            return new CategoriaCuentaDto(categoriaCuenta);

        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

    }

}
