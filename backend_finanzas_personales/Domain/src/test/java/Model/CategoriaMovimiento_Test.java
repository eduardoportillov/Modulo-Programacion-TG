package Model;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import core.BussinessRuleValidateExeption;

public class CategoriaMovimiento_Test {
    UUID keyUser = UUID.randomUUID();

    @Test
    public void constructor() throws BussinessRuleValidateExeption {
        Assert.assertNotNull(new CategoriaMovimiento("Categoria Test", keyUser));
    }
}
