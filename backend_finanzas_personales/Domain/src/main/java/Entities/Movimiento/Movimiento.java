package Entities.Movimiento;

import java.time.LocalDateTime;
import java.util.UUID;

import Entities.ValueObject.Monto;
import core.AggregateRoot;

public class Movimiento extends AggregateRoot<UUID> {
    public UUID keyCuentaOrigen;
    public UUID keyCuentaDestino;
    public String descripcion;
    public UUID KeyCategoria;
    public Monto monto;
    public LocalDateTime fecha;

    public Movimiento(UUID keyCuentaOrigen, UUID keyCuentaDestino, String descripcion, UUID keyCategoria, double monto,
            LocalDateTime fecha) {
        key = UUID.randomUUID();
        this.keyCuentaOrigen = keyCuentaOrigen;
        this.keyCuentaDestino = keyCuentaDestino;
        this.descripcion = descripcion;
        KeyCategoria = keyCategoria;
        this.monto = new Monto(monto);
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public UUID getKeyCategoria() {
        return KeyCategoria;
    }

    public Monto getMonto() {
        return monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public UUID getKeyCuentaOrigen() {
        return keyCuentaOrigen;
    }

    public UUID getKeyCuentaDestino() {
        return keyCuentaDestino;
    }

}
