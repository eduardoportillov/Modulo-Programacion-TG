package DomainEvents.Movimiento;

import java.util.UUID;

import core.DomainEvent;

public class MovimientoEditado extends DomainEvent {
    private UUID keyMovimiento;
    private double montoAntiguo;
    private double montoNuevo;

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
