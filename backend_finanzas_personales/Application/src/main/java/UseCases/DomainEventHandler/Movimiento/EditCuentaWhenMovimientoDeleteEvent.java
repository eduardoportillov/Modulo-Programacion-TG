package UseCases.DomainEventHandler.Movimiento;

import DomainEvents.Movimiento.MovimientoDelete;
import Fourteam.console.console;
import Fourteam.mediator.NotificationHandler;
import Model.Cuenta;
import Repositories.ICuentaRepository;
import core.ConfirmedDomainEvent;

public class EditCuentaWhenMovimientoDeleteEvent
        implements NotificationHandler<ConfirmedDomainEvent<MovimientoDelete>> {
    private ICuentaRepository _cuentaRepository;

    public EditCuentaWhenMovimientoDeleteEvent(ICuentaRepository _cuentaRepository) {
        this._cuentaRepository = _cuentaRepository;
    }

    @Override
    public void handle(ConfirmedDomainEvent<MovimientoDelete> notification) {
        ConfirmedDomainEvent event = (ConfirmedDomainEvent) notification;
        MovimientoDelete movimientoEvent = (MovimientoDelete) event.DomainEvent;
        try {

            double monto = movimientoEvent.getMonto();

            Cuenta cuentaOrigen = _cuentaRepository.FindByKey(movimientoEvent.getKeyCuentaOrigen());

            Cuenta cuentaDestino = _cuentaRepository.FindByKey(movimientoEvent.getKeyCuentaDestino());

            if (cuentaOrigen == null) {
                cuentaDestino.getMonto().restar(monto);
                _cuentaRepository.Update(cuentaDestino);
            }

            if (cuentaDestino == null) {
                cuentaOrigen.getMonto().restar(monto);
                _cuentaRepository.Update(cuentaOrigen);
            }

            if (cuentaOrigen != null && cuentaDestino != null) {
                cuentaOrigen.getMonto().sumar(monto);
                cuentaDestino.getMonto().restar(monto);

                _cuentaRepository.Update(cuentaOrigen);
                _cuentaRepository.Update(cuentaDestino);
            }
        } catch (Exception e) {
            console.error(e.getMessage());
        }

    }

}
