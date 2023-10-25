package UseCases.Queries.CategoriaMovimiento.GetAllCategoriaMoviminetoUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Dto.CategoriaMovimientoDto;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.ISecurityUtils;

public class GetAllCategoriaMoviminetoUserHandler
        implements RequestHandler<GetAllCategoriaMoviminetoUserQuery, List<CategoriaMovimientoDto>> {

    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;
    private ISecurityUtils _securityUtils;

    public GetAllCategoriaMoviminetoUserHandler(ICategoriaMovimientoRepository _categoriaMovimientoRepository,
            ISecurityUtils _securityUtils) {
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
        this._securityUtils = _securityUtils;
    }

    @Override
    public List<CategoriaMovimientoDto> handle(GetAllCategoriaMoviminetoUserQuery request) throws Exception {
        UUID keyUser;
        List<CategoriaMovimientoDto> listCategoriaMovimiento = new ArrayList<CategoriaMovimientoDto>();

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        try {
            _categoriaMovimientoRepository.GetAllByKeyUser(keyUser).forEach(cm -> {
                listCategoriaMovimiento.add(new CategoriaMovimientoDto(cm));
            });
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return listCategoriaMovimiento;
    }

}
