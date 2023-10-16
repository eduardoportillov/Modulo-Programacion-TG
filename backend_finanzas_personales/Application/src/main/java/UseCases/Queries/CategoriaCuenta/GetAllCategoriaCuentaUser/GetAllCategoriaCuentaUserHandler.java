package UseCases.Queries.CategoriaCuenta.GetAllCategoriaCuentaUser;

import java.util.ArrayList;
import java.util.List;

import Dto.CategoriaCuentaDto;
import Entities.User;
import Entities.Cuenta.CategoriaCuenta;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IUserRepository;

public class GetAllCategoriaCuentaUserHandler
        implements RequestHandler<GetAllCategoriaCuentaUserQuery, List<CategoriaCuentaDto>> {
    IUserRepository _userRepository;

    public GetAllCategoriaCuentaUserHandler(IUserRepository _userRepository) {
        this._userRepository = _userRepository;
    }

    @Override
    public List<CategoriaCuentaDto> handle(GetAllCategoriaCuentaUserQuery request) throws Exception {
        User user;
        List<CategoriaCuentaDto> listCategoriaCuenta = new ArrayList<CategoriaCuentaDto>();

        try {
            user = User.decodeTokenWithUser(request.token);

            user = _userRepository.FindByKey(user.key);
            for (CategoriaCuenta cc : user.getCategoriaCuentaUser()) {
                listCategoriaCuenta.add(new CategoriaCuentaDto(cc));
            }

        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        return  listCategoriaCuenta;
    }

}
