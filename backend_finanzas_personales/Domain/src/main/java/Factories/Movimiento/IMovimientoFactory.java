package Factories.Movimiento;

import java.time.LocalDateTime;
import java.util.UUID;

import Model.Movimiento;

public interface IMovimientoFactory {
    public Movimiento Create(
            UUID keyCuentaOrigen,
            UUID keyCuentaDestino,
            String descripcion,
            UUID categoria,
            double monto,
            LocalDateTime fecha);
}
