package DomainEvents.Movimiento;

import java.util.UUID;

import core.DomainEvent;

public class MovimientoDelete extends DomainEvent {
    private UUID keyCuentaOrigen;
    private UUID keyCuentaDestino;
    private double monto;


    public MovimientoDelete(UUID keyCuentaOrigen, UUID keyCuentaDestino, double monto) {
        this.keyCuentaOrigen = keyCuentaOrigen;
        this.keyCuentaDestino = keyCuentaDestino;
        this.monto = monto;
    }


    public UUID getKeyCuentaOrigen() {
        return keyCuentaOrigen;
    }


    public UUID getKeyCuentaDestino() {
        return keyCuentaDestino;
    }


    public double getMonto() {
        return monto;
    }


}
