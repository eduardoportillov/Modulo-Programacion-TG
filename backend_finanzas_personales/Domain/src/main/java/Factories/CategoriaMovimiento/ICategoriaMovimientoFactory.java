package Factories.CategoriaMovimiento;

import Entities.Movimiento.CategoriaMovimiento;

public interface ICategoriaMovimientoFactory {
    public CategoriaMovimiento Create(String nombre);
}
