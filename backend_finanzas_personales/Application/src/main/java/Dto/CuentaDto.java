package Dto;

import java.util.UUID;

import Model.Cuenta;

public class CuentaDto {
    public UUID key;
    public String nombre;
    public UUID keyUserTitular;
    public UUID keyCategoria;
    public double monto;

    public CuentaDto() {
    }

    public CuentaDto(UUID key, String nombre, UUID keyUserTitular, UUID keyCategoria, double monto) {
        this.key = key;
        this.nombre = nombre;
        this.keyUserTitular = keyUserTitular;
        this.keyCategoria = keyCategoria;
        this.monto = monto;
    }

    public CuentaDto(Cuenta cuenta) {
        this.key = cuenta.getKey();
        this.nombre = cuenta.getNombre();
        this.keyUserTitular = cuenta.getKeyUserTitular();
        this.keyCategoria = cuenta.getKeyCategoria();
        this.monto = cuenta.getMonto().getMonto();
    }

    public double getMonto(){
        return monto;
    }
}
