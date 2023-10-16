package Dto;

import java.util.UUID;

import Entities.Cuenta.CategoriaCuenta;

public class CategoriaCuentaDto {
    public UUID key;
    public String nombre;
    private boolean createCategoryDefault;

    public CategoriaCuentaDto() {
    }

    public CategoriaCuentaDto(UUID key, String nombre, boolean createCategoryDefault) {
        this.key = key;
        this.nombre = nombre;
        this.createCategoryDefault = createCategoryDefault;
    }

    public CategoriaCuentaDto(CategoriaCuenta categoriaCuenta) {
        this.key = categoriaCuenta.getKey();
        this.nombre = categoriaCuenta.getNombre();
    }

    public UUID getKey() {
        return key;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isCreateCategoryDefault() {
        return createCategoryDefault;
    }

}
