package Entities.Cuenta;

import java.util.UUID;

import DomainEvents.CuentaCreada;
import Entities.Cuenta.ValueObject.Monto;
import core.AggregateRoot;

public class Cuenta extends AggregateRoot<UUID> {
    public String nombre;
    public UUID keyUserTitular;
    public UUID keyCategoria;
    private Monto monto;

    public Cuenta(String nombre, UUID keyUserTitular, UUID keyCategoria) {
        key = UUID.randomUUID();
        this.nombre = nombre;
        this.keyUserTitular = keyUserTitular;
        this.keyCategoria = keyCategoria;
        this.monto = new Monto(0);
    }

    public void eventCreado(UUID keyCuenta, UUID keyCategoriaMovimiento, double monto) {
        addDomainEvent(new CuentaCreada(keyCuenta, keyCategoria, monto));
    }

    public void addMonto(double monto) {
        this.monto.sumar(monto);
    }

    public void restarMonto(double monto) {

        if (this.monto.getMonto() < monto) {
            throw new IllegalArgumentException("No hay saldo suficiente, para restar saldo");
        }

        this.monto.restar(monto);
    }

    public String getNombre() {
        return nombre;
    }

    public UUID getKeyUserTitular() {
        return keyUserTitular;
    }

    public UUID getKeyCategoria() {
        return keyCategoria;
    }

    public Monto getMonto() {
        return monto;
    }
}
