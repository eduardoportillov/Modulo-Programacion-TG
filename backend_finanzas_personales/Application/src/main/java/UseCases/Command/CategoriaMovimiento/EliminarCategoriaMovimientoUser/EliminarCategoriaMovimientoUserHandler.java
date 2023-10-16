package UseCases.Command.CategoriaMovimiento.EliminarCategoriaMovimientoUser;

import Entities.User;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;

public class EliminarCategoriaMovimientoUserHandler implements RequestHandler<EliminarCategoriaMovimientoUserCommand, String> {
    IUserRepository _userRepository;
    IUnitOfWork _unitOfWork;

    public EliminarCategoriaMovimientoUserHandler(IUserRepository userRepository) {
        this._userRepository = userRepository;
    }

    @Override
    public String handle(EliminarCategoriaMovimientoUserCommand request) throws Exception {
        User user;
        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        user = _userRepository.FindByKey(user.key);

        if (user.isCategoriaMovimientoUser(request.categoriaMovimiento.key)) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "No se encontro la categoria");
        }

        user.categoriaMovimientoUser.removeIf(cc -> cc.key.toString().equals(request.categoriaMovimiento.key.toString()));

        _userRepository.Update(user);

        return "Categoria Usuario Eliminada con Exito";

    }

}
