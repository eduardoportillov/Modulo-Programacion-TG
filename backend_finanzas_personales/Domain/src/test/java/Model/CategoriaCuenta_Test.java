package Model;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import core.BussinessRuleValidateExeption;

public class CategoriaCuenta_Test {
    UUID keyUser = UUID.randomUUID();

    @Test
    public void constructor() throws BussinessRuleValidateExeption {
        Assert.assertNotNull(
                new CategoriaCuenta("Categoria Test", keyUser, CategoriaCuenta.generarColorPastelAleatorio()));
    }

    @Test
    public void generarColorPastelAleatorio() {
        Assert.assertNotNull(CategoriaCuenta.generarColorPastelAleatorio());
    }
}
