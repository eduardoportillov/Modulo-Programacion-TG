package Dto;

import java.util.UUID;

import Entities.Movimiento.CategoriaMovimiento;

public class CategoriaMovimientoDto {
    public UUID key;
    public String nombre;
    public boolean createCategoryDefault;

    public CategoriaMovimientoDto() {
    }

    public CategoriaMovimientoDto(UUID key, String nombre, boolean createCategoryDefault) {
        this.key = key;
        this.nombre = nombre;
        this.createCategoryDefault = createCategoryDefault;
    }

    public CategoriaMovimientoDto(CategoriaMovimiento categoriaMovimiento) {
        this.key = categoriaMovimiento.getKey();
        this.nombre = categoriaMovimiento.getNombre();
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
