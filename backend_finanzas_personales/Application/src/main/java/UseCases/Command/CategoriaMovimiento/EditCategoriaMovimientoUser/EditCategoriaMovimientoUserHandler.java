package UseCases.Command.CategoriaMovimiento.EditCategoriaMovimientoUser;

import Entities.User;
import Entities.Cuenta.CategoriaCuenta;
import Entities.Cuenta.CategoriaMovimiento;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;

public class EditCategoriaMovimientoUserHandler implements RequestHandler<EditCategoriaMovimientoUserCommand, String> {
    private IUserRepository _userRepository;
    private IUnitOfWork _unitOfWork;

    public EditCategoriaMovimientoUserHandler(IUserRepository _userRepository, IUnitOfWork _unitOfWork) {
        this._userRepository = _userRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EditCategoriaMovimientoUserCommand request) throws Exception {
        User user;
        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }
        user = _userRepository.FindByKey(user.key);
        for (CategoriaMovimiento cc : user.categoriaMovimientoUser) {
            try {
                if (cc.key.toString().equals(request.categoriaMovimiento.key.toString())) {
                    cc.nombre = request.categoriaMovimiento.nombre;

                    _userRepository.Update(user);
                    _unitOfWork.commit();
                    return "Categoria Movimiento User editada con exito ";
                }
            } catch (Exception e) {
                throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        }

        return "Error";
    }

}
