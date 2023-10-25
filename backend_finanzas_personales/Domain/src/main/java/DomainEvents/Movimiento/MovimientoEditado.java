package DomainEvents.Movimiento;

import java.time.LocalDateTime;
import java.util.UUID;

import Entities.Movimiento;
import core.DomainEvent;

public class MovimientoEditado extends DomainEvent {
    // private UUID keyCuentaOrigen;
    // private UUID keyCuentaDestino;
    private UUID keyMovimiento;
    private double montoAntiguo;
    private double montoNuevo;

    // private Movimiento movimiento;

    public MovimientoEditado(UUID keyMovimiento, double montoAntiguo, double montoNuevo) {
        this.keyMovimiento = keyMovimiento;
        this.montoAntiguo = montoAntiguo;
        this.montoNuevo = montoNuevo;
    }

    public double getMontoAntiguo() {
        return montoAntiguo;
    }

    public double getMontoNuevo() {
        return montoNuevo;
    }

    public UUID getKeyMovimiento() {
        return keyMovimiento;
    }

}
