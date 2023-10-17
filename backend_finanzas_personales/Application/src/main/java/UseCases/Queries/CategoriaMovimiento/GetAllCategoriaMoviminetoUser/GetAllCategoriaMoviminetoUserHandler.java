package UseCases.Queries.CategoriaMovimiento.GetAllCategoriaMoviminetoUser;

import java.util.ArrayList;
import java.util.List;

import Dto.CategoriaMovimientoDto;
import Entities.User;
import Entities.Cuenta.CategoriaCuenta;
import Entities.Movimiento.CategoriaMovimiento;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUserRepository;

public class GetAllCategoriaMoviminetoUserHandler
        implements RequestHandler<GetAllCategoriaMoviminetoUserQuery, List<CategoriaMovimientoDto>> {
    IUserRepository _userRepository;

    public GetAllCategoriaMoviminetoUserHandler(IUserRepository _userRepository) {
        this._userRepository = _userRepository;
    }

    @Override
    public List<CategoriaMovimientoDto> handle(GetAllCategoriaMoviminetoUserQuery request) throws Exception {
        User user;
        List<CategoriaMovimientoDto> listCategoriaMovimiento = new ArrayList<CategoriaMovimientoDto>();

        try {
            user = User.decodeTokenWithUser(request.token);

            user = _userRepository.FindByKey(user.key);
            for (CategoriaMovimiento cm : user.getCategoriaMovimientoUser()) {
                listCategoriaMovimiento.add(new CategoriaMovimientoDto(cm));
            }

        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        return listCategoriaMovimiento;
    }

}
