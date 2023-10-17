package UseCases.DomainEventHandler.Cuenta;

import java.time.LocalDateTime;

import DomainEvents.CuentaCreada;
import Entities.Cuenta.Cuenta;
import Entities.Movimiento.Movimiento;
import Factories.Movimiento.IMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.NotificationHandler;
import Repositories.ICuentaRepository;
import Repositories.IMovimientoRepository;
import core.ConfirmedDomainEvent;

public class AddMovimientoEventWhenCuentaCreada implements NotificationHandler<ConfirmedDomainEvent<CuentaCreada>> {
    private IMovimientoFactory _movimientoFactory;
    private IMovimientoRepository _movimientoRepository;
    private ICuentaRepository _cuentaRepository;

    public AddMovimientoEventWhenCuentaCreada(IMovimientoFactory _movimientoFactory,
            IMovimientoRepository _movimientoRepository, ICuentaRepository _cuentaRepository) {
        this._movimientoFactory = _movimientoFactory;
        this._movimientoRepository = _movimientoRepository;
        this._cuentaRepository = _cuentaRepository;
    }

    @Override
    public void handle(ConfirmedDomainEvent<CuentaCreada> notification) {
        ConfirmedDomainEvent event = (ConfirmedDomainEvent) notification;
        CuentaCreada cuentaEvent = (CuentaCreada) event.DomainEvent;

        try {
            Cuenta cuenta = _cuentaRepository.FindByKey(cuentaEvent.keyCuenta);
            if (cuenta == null) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "La cuenta origen no existe");
            }

            Movimiento movimiento = _movimientoFactory.Create(
                    cuentaEvent.keyCuenta,
                    cuentaEvent.keyCuenta,
                    "Movimiento Inicial",
                    cuentaEvent.keyCategoria,
                    cuentaEvent.saldoInicial,
                    LocalDateTime.now());

            _movimientoRepository.Create(movimiento);
            cuenta.addMonto(cuentaEvent.saldoInicial);
            _cuentaRepository.Update(cuenta);
        } catch (Exception e) {
            new Exception(e.getMessage());
        }

    }

}
