package Model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

import core.BussinessRuleValidateExeption;
import Fourteam.console.console;

public class Movimiento_Test {
    UUID keyCuentaOrigen = UUID.randomUUID();
    UUID keyCuentaDestino = UUID.randomUUID();
    String descripcion = "Descripcion Test";
    UUID keyCategoria = UUID.randomUUID();
    double monto = 200.00;
    LocalDateTime fecha = LocalDateTime.now();

    Movimiento movimiento = new Movimiento(keyCuentaOrigen, keyCuentaDestino, descripcion, keyCategoria, monto, fecha);

    @Test
    public void constructor() throws BussinessRuleValidateExeption {
        Assert.assertNotNull(
                new Movimiento(keyCuentaOrigen, keyCuentaDestino, descripcion, keyCategoria, monto, fecha));
    }

    @Test
    public void eventoCreado() {
        try {
            UUID keyUser = UUID.randomUUID();
            movimiento.evenCreado(keyUser, movimiento.keyCuentaOrigen, movimiento.keyCuentaDestino, monto);
            Assert.assertEquals(movimiento.domainEvents.size(), 1);
        } catch (Exception e) {
            console.error(e.getMessage());
        }
    }

    @Test
    public void eventEdit() {
        try {
            movimiento.eventEdit(movimiento.key, (monto + 100), monto);
            Assert.assertEquals(movimiento.domainEvents.size(), 1);
        } catch (Exception e) {
            console.error(e.getMessage());
        }
    }

    @Test
    public void eventDelete() {
        try {
            movimiento.eventDelete(movimiento.getKeyCuentaOrigen(), movimiento.getKeyCuentaDestino(), monto);
            Assert.assertEquals(movimiento.domainEvents.size(), 1);
        } catch (Exception e) {
            console.error(e.getMessage());
        }
    }

}
