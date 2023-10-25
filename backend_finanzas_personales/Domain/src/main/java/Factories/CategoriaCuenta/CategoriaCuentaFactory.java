package Factories.CategoriaCuenta;

import java.util.UUID;

import Entities.CategoriaCuenta;

public class CategoriaCuentaFactory implements ICategoriaCuentaFactory{

    @Override
    public CategoriaCuenta Create(String nombre, UUID keyUser, String color) {
        return new CategoriaCuenta(nombre, keyUser, color);
    }
    
}
