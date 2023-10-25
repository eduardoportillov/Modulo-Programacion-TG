package UseCases.Command.Cuenta.AddMonto;

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

public class AddMontoCuentaHandler implements RequestHandler<AddMontoCuentaCommand, String> {

    private IMovimientoFactory _movimientoFactory;
    private IMovimientoRepository _movimientoRepository;

    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;

    private ICuentaRepository _cuentaRepository;

    private ISecurityUtils _securityUtils;

    public AddMontoCuentaHandler(IMovimientoFactory _movimientoFactory, IMovimientoRepository _movimientoRepository,
            ICategoriaMovimientoRepository _categoriaMovimientoRepository, ICuentaRepository _cuentaRepository,
            ISecurityUtils _securityUtils) {
        this._movimientoFactory = _movimientoFactory;
        this._movimientoRepository = _movimientoRepository;
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
        this._cuentaRepository = _cuentaRepository;
        this._securityUtils = _securityUtils;
    }

    @Override
    public String handle(AddMontoCuentaCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        try {
            Cuenta cuenta = _cuentaRepository.FindByKey(request.data.key);

            Movimiento movimiento = _movimientoFactory.Create(
                    null,
                    cuenta.key,
                    "Monto Add",
                    _categoriaMovimientoRepository.GetAllByKeyUser(keyUser).get(0).key,
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
