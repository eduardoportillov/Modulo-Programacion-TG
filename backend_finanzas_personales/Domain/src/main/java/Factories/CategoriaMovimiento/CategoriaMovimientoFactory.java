package Factories.CategoriaMovimiento;

import java.util.UUID;

import Entities.CategoriaMovimiento;

public class CategoriaMovimientoFactory implements ICategoriaMovimientoFactory{

    @Override
    public CategoriaMovimiento Create(String nombre, UUID keyUser) {
        return new CategoriaMovimiento(nombre, keyUser);
    }

    
}
