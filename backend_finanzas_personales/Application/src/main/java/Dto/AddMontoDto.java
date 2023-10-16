package Dto;

import java.util.UUID;

public class AddMontoDto {
    public UUID keyCategoria;
    public double monto;

    public AddMontoDto() {
    }

    public AddMontoDto(UUID keyCategoria, double monto) {
        this.keyCategoria = keyCategoria;
        this.monto = monto;
    }

    public UUID getKeyCategoria() {
        return keyCategoria;
    }

    public double getMonto() {
        return monto;
    }   



}
