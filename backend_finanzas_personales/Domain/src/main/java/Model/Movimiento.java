package Model;

import java.time.LocalDateTime;
import java.util.UUID;

import DomainEvents.Movimiento.MovimientoCreado;
import DomainEvents.Movimiento.MovimientoDelete;
import DomainEvents.Movimiento.MovimientoEditado;
import Fourteam.console.console;
import Model.ValueObject.Monto;
import core.AggregateRoot;

public class Movimiento extends AggregateRoot<UUID> {
    public UUID keyCuentaOrigen;
    public UUID keyCuentaDestino;
    public String descripcion;
    public UUID KeyCategoria;
    private Monto monto;
    public LocalDateTime fecha;

    public Movimiento(UUID keyCuentaOrigen, UUID keyCuentaDestino, String descripcion, UUID keyCategoria, double monto,
            LocalDateTime fecha) {
        super();
        key = UUID.randomUUID();
        this.keyCuentaOrigen = keyCuentaOrigen;
        this.keyCuentaDestino = keyCuentaDestino;
        this.descripcion = descripcion;
        KeyCategoria = keyCategoria;
        this.monto = new Monto(monto);
        this.fecha = fecha;
    }

    public void evenCreado(UUID keyUser, UUID keyCuentaOrigen, UUID keyCuentaDestino, double monto) {
        addDomainEvent(new MovimientoCreado(keyUser, keyCuentaOrigen, keyCuentaDestino, monto));
    }

    public void eventEdit(UUID keyMovimiento, double montoAntiguo, double montoNuevo) {
        addDomainEvent(new MovimientoEditado(keyMovimiento, montoAntiguo, montoNuevo));
    }

    public void eventDelete(UUID keyCuentaOrigen, UUID keyCuentaDestino,double monto) {
        addDomainEvent(new MovimientoDelete(keyCuentaOrigen, keyCuentaDestino, monto));
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

    public void setKeyCuentaOrigen(UUID keyCuentaOrigen) {
        this.keyCuentaOrigen = keyCuentaOrigen;
    }

    public void setKeyCuentaDestino(UUID keyCuentaDestino) {
        this.keyCuentaDestino = keyCuentaDestino;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setKeyCategoria(UUID keyCategoria) {
        KeyCategoria = keyCategoria;
    }

    public void setMonto(Monto monto) {
        this.monto = monto;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}
