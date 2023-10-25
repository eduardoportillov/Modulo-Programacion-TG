package Model;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import Fourteam.console.console;
import core.BussinessRuleValidateExeption;

public class Cuenta_Test {
    UUID keyOrigen = UUID.randomUUID();
    UUID keyDestino = UUID.randomUUID();

    @Test
    public void constructor() throws BussinessRuleValidateExeption {
        Assert.assertNotNull(new Cuenta("Cuenta Pruebas", keyOrigen, keyDestino));
    }

    @Test
    public void eventoCreado() {
        try {
            UUID keyCategoriaMovimiento = UUID.randomUUID();
            double monto = 200.00;

            Cuenta cuenta = new Cuenta("Cuenta Pruebas", keyOrigen, keyDestino);
            cuenta.eventCreado(cuenta.key, keyCategoriaMovimiento, monto);
            Assert.assertEquals(cuenta.domainEvents.size(), 1);
        } catch (Exception e) {
            console.error(e.getMessage());
        }
    }

    @Test
    public void addMonto() {
        try {
            Cuenta cuenta = new Cuenta("Cuenta Pruebas", keyOrigen, keyDestino);
            cuenta.addMonto(200.00);

            Assert.assertEquals(cuenta.getMonto().getMonto(), 200.00, 0);
        } catch (Exception e) {
            console.error(e.getMessage());
        }
    }

    @Test
    public void restarMonto() {
        try {
            Cuenta cuenta = new Cuenta("Cuenta Pruebas", keyOrigen, keyDestino);
            cuenta.restarMonto(200.00);

            Assert.assertEquals(cuenta.getMonto().getMonto(), -200.00, 0);
        } catch (Exception e) {
            console.error(e.getMessage());
        }
    }
}
