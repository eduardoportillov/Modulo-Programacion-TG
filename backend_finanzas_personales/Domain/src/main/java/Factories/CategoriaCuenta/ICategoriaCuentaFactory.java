package Factories.CategoriaCuenta;

import java.util.UUID;

import Model.CategoriaCuenta;

public interface ICategoriaCuentaFactory {
    public CategoriaCuenta Create(String nombre, UUID keyUser, String color);
}
