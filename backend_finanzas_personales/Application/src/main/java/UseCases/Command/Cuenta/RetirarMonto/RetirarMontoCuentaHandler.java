package UseCases.Command.Cuenta.RetirarMonto;

import java.time.LocalDateTime;
import java.util.UUID;

import Factories.Movimiento.IMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.Cuenta;
import Model.Movimiento;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.ICuentaRepository;
import Repositories.IMovimientoRepository;
import Repositories.ISecurityUtils;

public class RetirarMontoCuentaHandler implements RequestHandler<RetirarMontoCuentaCommand, String> {

    private IMovimientoFactory _movimientoFactory;
    private IMovimientoRepository _movimientoRepository;

    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;

    private ISecurityUtils _securityUtils;

    private ICuentaRepository _cuentaRepository;

    public RetirarMontoCuentaHandler(IMovimientoFactory _movimientoFactory, IMovimientoRepository _movimientoRepository,
            ICategoriaMovimientoRepository _categoriaMovimientoRepository, ISecurityUtils _securityUtils,
            ICuentaRepository _cuentaRepository) {
        this._movimientoFactory = _movimientoFactory;
        this._movimientoRepository = _movimientoRepository;
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
        this._securityUtils = _securityUtils;
        this._cuentaRepository = _cuentaRepository;
    }

    @Override
    public String handle(RetirarMontoCuentaCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        try {
            Cuenta cuenta = _cuentaRepository.FindByKey(request.data.key);

            // @VALIDACIÓN_NO_MONTO_NEGATIVO
            // if(cuenta.getMonto().getMonto() < request.data.monto){
            // throw new HttpException(HttpStatus.BAD_REQUEST, "No se puede retirar mas de
            // lo que hay en la cuenta");
            // }

            // double monto = request.data.monto * -1;
            double monto = request.data.monto;

            Movimiento movimiento = _movimientoFactory.Create(
                    cuenta.key,
                    null,
                    "Monto retirado de la cuenta",
                    _categoriaMovimientoRepository.GetAllByKeyUser(keyUser).get(0).key,
                    monto,
                    LocalDateTime.now());
            _movimientoRepository.Create(movimiento);

            cuenta.restarMonto(request.data.monto);
            _cuentaRepository.Update(cuenta);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

        return "Movimiento Creado con exito al retirar monto de la cuenta";
    }

}
