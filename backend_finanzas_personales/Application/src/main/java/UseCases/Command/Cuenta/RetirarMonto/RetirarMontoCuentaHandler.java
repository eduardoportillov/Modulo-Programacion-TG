package UseCases.Command.Cuenta.RetirarMonto;

import java.time.LocalDateTime;

import Entities.User;
import Entities.Cuenta.Cuenta;
import Entities.Movimiento.Movimiento;
import Factories.Movimiento.IMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.IMovimientoRepository;
import Repositories.IUserRepository;

public class RetirarMontoCuentaHandler implements RequestHandler<RetirarMontoCuentaCommand, String> {

    private IMovimientoFactory _movimientoFactory;
    private IMovimientoRepository _movimientoRepository;
    private IUserRepository _userRepository;
    private ICuentaRepository _cuentaRepository;



    public RetirarMontoCuentaHandler(IMovimientoFactory _movimientoFactory, IMovimientoRepository _movimientoRepository,
            IUserRepository _userRepository, ICuentaRepository _cuentaRepository) {
        this._movimientoFactory = _movimientoFactory;
        this._movimientoRepository = _movimientoRepository;
        this._userRepository = _userRepository;
        this._cuentaRepository = _cuentaRepository;
    }



    @Override
    public String handle(RetirarMontoCuentaCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        try {
            Cuenta cuenta = _cuentaRepository.FindByKey(request.data.key);

            user = _userRepository.FindByKey(user.key);

            // @VALIDACIÓN_NO_MONTO_NEGATIVO
            // if(cuenta.getMonto().getMonto() < request.data.monto){
            //     throw new HttpException(HttpStatus.BAD_REQUEST, "No se puede retirar mas de lo que hay en la cuenta");
            // }

            double monto = request.data.monto * -1;

            Movimiento movimiento = _movimientoFactory.Create(
                    cuenta.key,
                    null,
                    "Monto retirado de la cuenta",
                    user.categoriaMovimientoUser.get(0).key,
                    monto,
                    LocalDateTime.now());
            _movimientoRepository.Create(movimiento);

            cuenta.restarMonto(request.data.monto);
            _cuentaRepository.Update(cuenta);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return "Movimiento Creado con exito al retirar monto a la cuenta";
    }

}
