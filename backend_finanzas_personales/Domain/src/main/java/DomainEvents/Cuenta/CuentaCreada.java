package DomainEvents.Cuenta;

import java.util.UUID;

import core.DomainEvent;

public class CuentaCreada extends DomainEvent {
    public UUID keyCuenta;
    public UUID keyCategoria;
    public double saldoInicial;

    public CuentaCreada(UUID keyCuenta, UUID keyCategoria, double saldoInicial) {
        this.keyCuenta = keyCuenta;
        this.keyCategoria = keyCategoria;
        this.saldoInicial = saldoInicial;
    }
}
