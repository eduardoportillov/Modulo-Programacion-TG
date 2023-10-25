package DomainEvents.Movimiento;

import java.util.UUID;

import core.DomainEvent;

public class MovimientoCreado extends DomainEvent {
    private UUID keyUser;
    private UUID keyCuentaOrigen;
    private UUID keyCuentaDestino;

    private double monto;

    public MovimientoCreado(UUID keyUser, UUID keyCuentaOrigen, UUID keyCuentaDestino, double monto) {
        this.keyUser = keyUser;
        this.keyCuentaOrigen = keyCuentaOrigen;
        this.keyCuentaDestino = keyCuentaDestino;
        this.monto = monto;
    }

    public UUID getKeyUser() {
        return keyUser;
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
