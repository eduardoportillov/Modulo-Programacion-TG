package Factories.Movimiento;

import java.time.LocalDateTime;
import java.util.UUID;

import Model.Movimiento;

public class MovimientoFactory implements IMovimientoFactory {

    @Override
    public Movimiento Create(UUID keyCuentaOrigen, UUID keyCuentaDestino, String descripcion, UUID categoria,
            double monto, LocalDateTime fecha) {
        return new Movimiento(keyCuentaOrigen, keyCuentaDestino, descripcion, categoria, monto, fecha);
    }

}
