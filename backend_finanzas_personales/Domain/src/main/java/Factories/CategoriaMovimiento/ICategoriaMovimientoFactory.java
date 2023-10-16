package Factories.CategoriaMovimiento;

import Entities.Cuenta.CategoriaMovimiento;

public interface ICategoriaMovimientoFactory {
    public CategoriaMovimiento Create(String nombre);
}
