package UseCases.Queries.Movimiento.GetMovimientoByUser;

import java.util.ArrayList;
import java.util.List;

import Dto.MovimientoDto;
import Entities.User;
import Entities.Movimiento.Movimiento;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.IMovimientoRepository;

public class GetMovimientoByUserHandler implements RequestHandler<GetMovimientoByUserQuery, List<MovimientoDto>> {

    IMovimientoRepository _movimientoRepository;

    public GetMovimientoByUserHandler(IMovimientoRepository _movimientoRepository) {
        this._movimientoRepository = _movimientoRepository;
    }

    @Override
    public List<MovimientoDto> handle(GetMovimientoByUserQuery request) throws Exception {
        try {
            User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        List<Movimiento> listMovimiento = _movimientoRepository.GetByUser(request.keyUser);

        List<MovimientoDto> listMovimientoDto = new ArrayList<MovimientoDto>();

        for (Movimiento lm : listMovimiento) {
            listMovimientoDto.add(new MovimientoDto(lm));
        }

        return listMovimientoDto;
    }

}
