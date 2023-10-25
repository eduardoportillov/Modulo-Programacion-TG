package Dto;

import java.util.UUID;

import Model.CategoriaCuenta;

public class CategoriaCuentaDto {
    public UUID key;
    public String nombre;

    private UUID keyUser;

    private String color;

    private boolean createCategoryDefault;

    public CategoriaCuentaDto() {
    }

    public CategoriaCuentaDto(UUID key, String nombre, UUID keyUser, String color, boolean createCategoryDefault) {
        this.key = key;
        this.nombre = nombre;
        this.createCategoryDefault = createCategoryDefault;
        this.keyUser = keyUser;
        this.color = color;
    }

    public CategoriaCuentaDto(CategoriaCuenta categoriaCuenta) {
        this.key = categoriaCuenta.getKey();
        this.nombre = categoriaCuenta.getNombre();
        this.keyUser = categoriaCuenta.getKeyUser();
        this.color = categoriaCuenta.getColor();
    }

    public UUID getKey() {
        return key;
    }

    public String getNombre() {
        return nombre;
    }

    public UUID getKeyUser() {
        return keyUser;
    }

    public boolean isCreateCategoryDefault() {
        return createCategoryDefault;
    }

    public void setKey(UUID key) {
        this.key = key;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setKeyUser(UUID keyUser) {
        this.keyUser = keyUser;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setCreateCategoryDefault(boolean createCategoryDefault) {
        this.createCategoryDefault = createCategoryDefault;
    }

}
