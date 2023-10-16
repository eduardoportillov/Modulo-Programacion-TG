package UseCases.Command.CategoriaCuenta.EliminarCategoriaCuentaUser;

import Entities.User;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;

public class EliminarCategoriaCuentaUserHandler implements RequestHandler<EliminarCategoriaCuentaUserCommand, String> {
    IUserRepository _userRepository;
    IUnitOfWork _unitOfWork;

    public EliminarCategoriaCuentaUserHandler(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public String handle(EliminarCategoriaCuentaUserCommand request) throws Exception {
        User user;
        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        user = _userRepository.FindByKey(user.key);

        if (user.isCategoriaCuentaUser(request.categoriacuenta.key)) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "No se encontro la categoria");
        }

        user.categoriaCuentaUser.removeIf(cc -> cc.key.toString().equals(request.categoriacuenta.key.toString()));

        _userRepository.Update(user);

        return "Categoria Usuario Eliminada con Exito";

    }

}
