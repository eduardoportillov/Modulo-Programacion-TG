package Entities.Cuenta;

import java.util.UUID;

import core.Entity;

public class CategoriaCuenta extends Entity<UUID> {
    public String nombre;

    public CategoriaCuenta(String nombre) {
        key = UUID.randomUUID();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
