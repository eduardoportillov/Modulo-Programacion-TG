package UseCases.Command.CategoriaCuenta.EditCategoriaCuentaUser;

import Entities.User;
import Entities.Cuenta.CategoriaCuenta;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;

public class EditCategoriaCuentaUserHandler implements RequestHandler<EditCategoriaCuentaUserCommand, String> {
    private IUserRepository _userRepository;
    private IUnitOfWork _unitOfWork;

    public EditCategoriaCuentaUserHandler(IUserRepository _userRepository, IUnitOfWork _unitOfWork) {
        this._userRepository = _userRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EditCategoriaCuentaUserCommand request) throws Exception {
        User user;
        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }
        user = _userRepository.FindByKey(user.key);
        for (CategoriaCuenta cc : user.categoriaCuentaUser) {
            try {
                if (cc.key.toString().equals(request.categoriacuenta.key.toString())) {
                    cc.nombre = request.categoriacuenta.nombre;

                    _userRepository.Update(user);
                    _unitOfWork.commit();
                    return "Categoria Cuenta User editada con exito ";
                }
            } catch (Exception e) {
                throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
            }

        }

        return "Error";
    }

}
