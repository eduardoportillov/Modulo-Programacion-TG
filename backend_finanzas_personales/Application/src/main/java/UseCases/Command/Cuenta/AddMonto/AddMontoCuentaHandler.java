package UseCases.Command.Cuenta.AddMonto;

import java.time.LocalDateTime;

import Entities.User;
import Entities.Cuenta.Cuenta;
import Entities.Cuenta.Movimiento;
import Factories.Movimiento.IMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.IMovimientoRepository;
import Repositories.IUserRepository;

public class AddMontoCuentaHandler implements RequestHandler<AddMontoCuentaCommand, String> {

    private IMovimientoFactory _movimientoFactory;
    private IMovimientoRepository _movimientoRepository;
    private IUserRepository _userRepository;
    private ICuentaRepository _cuentaRepository;



    public AddMontoCuentaHandler(IMovimientoFactory _movimientoFactory, IMovimientoRepository _movimientoRepository,
            IUserRepository _userRepository, ICuentaRepository _cuentaRepository) {
        this._movimientoFactory = _movimientoFactory;
        this._movimientoRepository = _movimientoRepository;
        this._userRepository = _userRepository;
        this._cuentaRepository = _cuentaRepository;
    }



    @Override
    public String handle(AddMontoCuentaCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        try {
            Cuenta cuenta = _cuentaRepository.FindByKey(request.data.key);

            user = _userRepository.FindByKey(user.key);

            Movimiento movimiento = _movimientoFactory.Create(
                    null,
                    cuenta.key,
                    "Monto añadido a la cuenta",
                    user.categoriaMovimientoUser.get(0).key,
                    request.data.monto,
                    LocalDateTime.now());
            _movimientoRepository.Create(movimiento);

            cuenta.addMonto(request.data.monto);
            _cuentaRepository.Update(cuenta);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return "Movimiento Creado con exito al añadir monto a la cuenta";
    }

}
