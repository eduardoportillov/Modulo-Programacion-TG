package UseCases.DomainEventHandler.Movimiento;

import DomainEvents.Movimiento.MovimientoEditado;
import Fourteam.console.console;
import Fourteam.mediator.NotificationHandler;
import Model.Cuenta;
import Model.Movimiento;
import Repositories.ICuentaRepository;
import Repositories.IMovimientoRepository;
import core.ConfirmedDomainEvent;

public class EditCuentaWhenMovimientoEditEvent implements NotificationHandler<ConfirmedDomainEvent<MovimientoEditado>> {
    private IMovimientoRepository _movimientoRepository;
    private ICuentaRepository _cuentaRepository;

    public EditCuentaWhenMovimientoEditEvent(IMovimientoRepository _movimientoRepository,
            ICuentaRepository _cuentaRepository) {
        this._movimientoRepository = _movimientoRepository;
        this._cuentaRepository = _cuentaRepository;
    }

    @Override
    public void handle(ConfirmedDomainEvent<MovimientoEditado> notification) {

        ConfirmedDomainEvent event = (ConfirmedDomainEvent) notification;
        MovimientoEditado movimientoEvent = (MovimientoEditado) event.DomainEvent;

        try {
            double diferencia;
            Movimiento movimiento = _movimientoRepository.FindByKey(movimientoEvent.getKeyMovimiento());

            double montoNuevo = movimientoEvent.getMontoNuevo();
            double montoAntiguo = movimientoEvent.getMontoAntiguo();

            Cuenta cuentaOrigen = _cuentaRepository.FindByKey(movimiento.getKeyCuentaOrigen());

            Cuenta cuentaDestino = _cuentaRepository.FindByKey(movimiento.getKeyCuentaDestino());

            if (cuentaOrigen == null) {
                diferencia = montoNuevo - montoAntiguo;
                cuentaDestino.getMonto().sumar(diferencia);
                _cuentaRepository.Update(cuentaDestino);
            }

            if (cuentaDestino == null) {
                diferencia = montoNuevo - montoAntiguo;
                cuentaOrigen.getMonto().sumar(diferencia);
                _cuentaRepository.Update(cuentaOrigen);
            }

            if (cuentaOrigen != null && cuentaDestino != null) {
                diferencia = montoNuevo - montoAntiguo;

                cuentaOrigen.getMonto().restar(diferencia);
                cuentaDestino.getMonto().sumar(diferencia);

                _cuentaRepository.Update(cuentaOrigen);
                _cuentaRepository.Update(cuentaDestino);
            }

        } catch (Exception e) {
            console.error(e.getMessage());
        }

    }

}
