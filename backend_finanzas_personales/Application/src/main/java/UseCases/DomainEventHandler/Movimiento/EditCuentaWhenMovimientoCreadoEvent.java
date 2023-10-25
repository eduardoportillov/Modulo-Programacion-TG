package UseCases.DomainEventHandler.Movimiento;

import DomainEvents.Movimiento.MovimientoCreado;
import Entities.Cuenta;
import Fourteam.console.console;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.NotificationHandler;
import Repositories.ICuentaRepository;
import core.ConfirmedDomainEvent;

public class EditCuentaWhenMovimientoCreadoEvent
        implements NotificationHandler<ConfirmedDomainEvent<MovimientoCreado>> {

    private ICuentaRepository _cuentaRepository;

    public EditCuentaWhenMovimientoCreadoEvent(ICuentaRepository _cuentaRepository) {
        this._cuentaRepository = _cuentaRepository;
    }

    @Override
    public void handle(ConfirmedDomainEvent<MovimientoCreado> notification) {
        ConfirmedDomainEvent event = (ConfirmedDomainEvent) notification;
        MovimientoCreado movimientoEvent = (MovimientoCreado) event.DomainEvent;

        try {
            Cuenta cuentaOrigen = _cuentaRepository.FindByKey(movimientoEvent.getKeyCuentaOrigen());

            // @VALIDACIÓN_NO_MONTO_NEGATIVO
            // if (cuentaOrigen.getMonto().getMonto() < request.data.monto) {
            // throw new HttpException(HttpStatus.BAD_REQUEST, "La cuenta origen no tiene
            // saldo suficiente");
            // }

            Cuenta cuentaDestino = _cuentaRepository.FindByKey(movimientoEvent.getKeyCuentaDestino());

            if (cuentaDestino == null) {
                cuentaOrigen.restarMonto(movimientoEvent.getMonto());
                _cuentaRepository.Update(cuentaOrigen);
            }

            if (cuentaOrigen == null) {
                cuentaDestino.restarMonto(movimientoEvent.getMonto());
                _cuentaRepository.Update(cuentaDestino);
            }

            if (cuentaOrigen != null && cuentaDestino != null) {
                cuentaOrigen.restarMonto(movimientoEvent.getMonto());
                cuentaDestino.addMonto(movimientoEvent.getMonto());

                _cuentaRepository.Update(cuentaOrigen);
                _cuentaRepository.Update(cuentaDestino);

            }

        } catch (Exception e) {
            console.error(e.getMessage());
        }

    }

}
