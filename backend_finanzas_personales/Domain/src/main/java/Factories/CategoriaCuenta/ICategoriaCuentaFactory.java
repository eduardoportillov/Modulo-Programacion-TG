package Factories.CategoriaCuenta;

import Entities.Cuenta.CategoriaCuenta;

public interface ICategoriaCuentaFactory {
    public CategoriaCuenta Create(String nombre);
}
