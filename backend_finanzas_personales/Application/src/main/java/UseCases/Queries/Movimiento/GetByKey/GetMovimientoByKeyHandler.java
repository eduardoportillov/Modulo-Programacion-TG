package UseCases.Queries.Movimiento.GetByKey;

import Dto.MovimientoDto;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.Movimiento;
import Repositories.IMovimientoRepository;
import Repositories.ISecurityUtils;

public class GetMovimientoByKeyHandler implements RequestHandler<GetMovimientoByKeyQuery, MovimientoDto> {
    IMovimientoRepository _movimientoRepository;

    private ISecurityUtils _securityUtils;

    public GetMovimientoByKeyHandler(IMovimientoRepository movimientoRepository, ISecurityUtils securityUtils) {
        _movimientoRepository = movimientoRepository;
        _securityUtils = securityUtils;
    }

    @Override
    public MovimientoDto handle(GetMovimientoByKeyQuery request) throws Exception {
        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        Movimiento movimiento = _movimientoRepository.FindByKey(request.key);
        if (movimiento == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Cuenta no encontrado");
        }
        MovimientoDto movimientoDto = new MovimientoDto(movimiento);
        return movimientoDto;
    }

}
