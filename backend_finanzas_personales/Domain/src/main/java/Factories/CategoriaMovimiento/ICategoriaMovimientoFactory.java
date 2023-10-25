package Factories.CategoriaMovimiento;

import java.util.UUID;

import Model.CategoriaMovimiento;

public interface ICategoriaMovimientoFactory {
    public CategoriaMovimiento Create(String nombre, UUID keyUser);
}
