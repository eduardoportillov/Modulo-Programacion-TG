package Dto;

import java.util.UUID;

import Model.CategoriaMovimiento;

public class CategoriaMovimientoDto {
    public UUID key;
    public String nombre;
    private UUID keyUser;
    public boolean createCategoryDefault;

    public CategoriaMovimientoDto() {
    }

    public CategoriaMovimientoDto(UUID key, String nombre, UUID keyUser, boolean createCategoryDefault) {
        this.key = key;
        this.nombre = nombre;
        this.keyUser = keyUser;
        this.createCategoryDefault = createCategoryDefault;
    }

    public CategoriaMovimientoDto(CategoriaMovimiento categoriaMovimiento) {
        this.key = categoriaMovimiento.getKey();
        this.nombre = categoriaMovimiento.getNombre();
        this.keyUser = categoriaMovimiento.getKeyUser();
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
