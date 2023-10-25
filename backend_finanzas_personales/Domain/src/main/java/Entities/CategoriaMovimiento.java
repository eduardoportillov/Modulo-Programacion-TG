package Entities;

import java.util.UUID;

import core.AggregateRoot;

public class CategoriaMovimiento extends AggregateRoot<UUID> {
    private String nombre;
    private UUID keyUser;

    public CategoriaMovimiento(String nombre, UUID keyUser) {
        key = UUID.randomUUID();
        this.nombre = nombre;
        this.keyUser = keyUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UUID getKeyUser() {
        return keyUser;
    }

    public void setKeyUser(UUID keyUser) {
        this.keyUser = keyUser;
    }

}
