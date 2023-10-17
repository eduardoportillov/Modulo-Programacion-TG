package Entities.Movimiento;

import java.util.UUID;

import core.Entity;

public class CategoriaMovimiento extends Entity<UUID> {
    public String nombre;

    public CategoriaMovimiento(String nombre) {
        key = UUID.randomUUID();
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
