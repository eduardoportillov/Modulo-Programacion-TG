package Factories.Cuenta;

import java.util.UUID;

import Entities.Cuenta.Cuenta;

public interface ICuentaFactory {
    public Cuenta Create(String nombre, UUID keyUserTitular, UUID categoria);
}
