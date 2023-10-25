package UseCases.Queries.Movimiento.GetMovimientoByCuenta;

import java.util.ArrayList;
import java.util.List;

import Dto.MovimientoDto;
import Entities.Movimiento;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IMovimientoRepository;
import Repositories.ISecurityUtils;

public class GetMovimientoByCuentaHandler implements RequestHandler<GetMovimientoByCuentaQuery, List<MovimientoDto>> {

    IMovimientoRepository _movimientoRepository;

    private ISecurityUtils _securityUtils;

    public GetMovimientoByCuentaHandler(IMovimientoRepository _movimientoRepository, ISecurityUtils _securityUtils) {
        this._movimientoRepository = _movimientoRepository;
        this._securityUtils = _securityUtils;
    }

    @Override
    public List<MovimientoDto> handle(GetMovimientoByCuentaQuery request) throws Exception {
        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        List<Movimiento> listMovimiento = _movimientoRepository.GetByCuenta(request.keyCuenta);

        List<MovimientoDto> listMovimientoDto = new ArrayList<MovimientoDto>();

        for (Movimiento lm : listMovimiento) {
            listMovimientoDto.add(new MovimientoDto(lm));
        }

        return listMovimientoDto;
    }

}
