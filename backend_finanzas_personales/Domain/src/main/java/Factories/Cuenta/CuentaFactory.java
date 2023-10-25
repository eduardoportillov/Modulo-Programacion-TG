package Factories.Cuenta;

import java.util.UUID;

import Model.Cuenta;

public class CuentaFactory implements ICuentaFactory {

    @Override
    public Cuenta Create(String nombre, UUID keyUserTitular, UUID categoria){
        return new Cuenta(nombre, keyUserTitular, categoria);
    }


}
