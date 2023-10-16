package UseCases.Command.CategoriaMovimiento.CreateCategoriaMovimientoUser;

import Dto.CategoriaCuentaDto;
import Dto.CategoriaMovimientoDto;
import Entities.User;
import Entities.Cuenta.CategoriaMovimiento;
import Factories.CategoriaMovimiento.ICategoriaMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;

public class CreateCategoriaMovimientoUserHandler
        implements RequestHandler<CreateCategoriaMovimientoUserCommand, CategoriaMovimientoDto> {
    private IUserRepository _userRepository;
    private ICategoriaMovimientoFactory _movimientoCategoriaFactory;
    private IUnitOfWork _unitOfWork;

    public CreateCategoriaMovimientoUserHandler(IUserRepository _userRepository,
            ICategoriaMovimientoFactory _movimientoCategoriaFactory, IUnitOfWork _unitOfWork) {
        this._userRepository = _userRepository;
        this._movimientoCategoriaFactory = _movimientoCategoriaFactory;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public CategoriaMovimientoDto handle(CreateCategoriaMovimientoUserCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
            user = _userRepository.FindByKey(user.key);

            CategoriaMovimiento categoriaCuenta = _movimientoCategoriaFactory.Create(request.cm.getNombre());

            user.addCategoriaMovimientoUser(categoriaCuenta);

            _userRepository.Update(user);
            _unitOfWork.commit();

            return new CategoriaMovimientoDto(categoriaCuenta);

        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

    }

}
