package UseCases.Queries.Movimiento.GetByKey;

import Dto.CuentaDto;
import Dto.MovimientoDto;
import Entities.User;
import Entities.Cuenta.Cuenta;
import Entities.Cuenta.Movimiento;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IMovimientoRepository;

public class GetMovimientoByKeyHandler implements RequestHandler<GetMovimientoByKeyQuery, MovimientoDto> {
    IMovimientoRepository _movimientoRepository;

    public GetMovimientoByKeyHandler(IMovimientoRepository _movimientoRepository) {
        this._movimientoRepository = _movimientoRepository;
    }

    @Override
    public MovimientoDto handle(GetMovimientoByKeyQuery request) throws Exception {
        try {
            User.decodeTokenWithUser(request.token);
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
