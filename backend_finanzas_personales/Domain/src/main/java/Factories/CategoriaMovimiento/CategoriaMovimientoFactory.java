package Factories.CategoriaMovimiento;

import Entities.Cuenta.CategoriaMovimiento;

public class CategoriaMovimientoFactory implements ICategoriaMovimientoFactory{

    @Override
    public CategoriaMovimiento Create(String nombre) {
        return new CategoriaMovimiento(nombre);
    }

    
}
