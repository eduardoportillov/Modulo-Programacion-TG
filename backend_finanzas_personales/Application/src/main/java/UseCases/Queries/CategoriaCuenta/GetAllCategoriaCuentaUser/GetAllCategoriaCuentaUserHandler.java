package UseCases.Queries.CategoriaCuenta.GetAllCategoriaCuentaUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Dto.CategoriaCuentaDto;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.CategoriaCuenta;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ISecurityUtils;

public class GetAllCategoriaCuentaUserHandler
        implements RequestHandler<GetAllCategoriaCuentaUserQuery, List<CategoriaCuentaDto>> {
    private ICategoriaCuentaRepository _categoriaCuentaRepository;

    private ISecurityUtils _SecurityUtils;

    public GetAllCategoriaCuentaUserHandler(ICategoriaCuentaRepository _categoriaCuentaRepository,
            ISecurityUtils _SecurityUtils) {
        this._categoriaCuentaRepository = _categoriaCuentaRepository;
        this._SecurityUtils = _SecurityUtils;
    }

    @Override
    public List<CategoriaCuentaDto> handle(GetAllCategoriaCuentaUserQuery request) throws Exception {
        UUID keyUser;
        List<CategoriaCuentaDto> listCategoriaCuenta = new ArrayList<CategoriaCuentaDto>();

        try {
            keyUser = _SecurityUtils.decodeToken(request.token);

        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        try {
            for (CategoriaCuenta cc : _categoriaCuentaRepository.GetAllByKeyUser(keyUser)) {
                if (cc.getKeyUser().equals(keyUser)) {
                    listCategoriaCuenta.add(new CategoriaCuentaDto(cc));
                }
            }
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return listCategoriaCuenta;
    }

}
