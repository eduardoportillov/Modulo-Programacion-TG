package UseCases.Queries.Movimiento.GetMovimientoByUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Dto.MovimientoDto;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.Movimiento;
import Repositories.IMovimientoRepository;
import Repositories.ISecurityUtils;

public class GetMovimientoByUserHandler implements RequestHandler<GetMovimientoByUserQuery, List<MovimientoDto>> {

    IMovimientoRepository _movimientoRepository;

    private ISecurityUtils _securityUtils;

    public GetMovimientoByUserHandler(IMovimientoRepository _movimientoRepository, ISecurityUtils _securityUtils) {
        this._movimientoRepository = _movimientoRepository;
        this._securityUtils = _securityUtils;
    }

    @Override
    public List<MovimientoDto> handle(GetMovimientoByUserQuery request) throws Exception {
        UUID keUser;
        try {
            keUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        List<Movimiento> listMovimiento = _movimientoRepository.GetByUser(keUser);

        List<MovimientoDto> listMovimientoDto = new ArrayList<MovimientoDto>();

        for (Movimiento lm : listMovimiento) {
            listMovimientoDto.add(new MovimientoDto(lm));
        }

        return listMovimientoDto;
    }

}
