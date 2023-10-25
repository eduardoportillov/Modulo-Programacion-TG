package Factories.Cuenta;

import java.util.UUID;

import Model.Cuenta;

public interface ICuentaFactory {
    public Cuenta Create(String nombre, UUID keyUserTitular, UUID categoria);
}
