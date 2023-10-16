package Factories.CategoriaCuenta;

import Entities.Cuenta.CategoriaCuenta;

public class CategoriaCuentaFactory implements ICategoriaCuentaFactory{

    @Override
    public CategoriaCuenta Create(String nombre) {
        return new CategoriaCuenta(nombre);
    }
    
}
