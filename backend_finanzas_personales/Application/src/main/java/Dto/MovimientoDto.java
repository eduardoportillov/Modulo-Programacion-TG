package Dto;

import java.time.LocalDateTime;
import java.util.UUID;

import Entities.Movimiento.Movimiento;

public class MovimientoDto {
    public UUID key;
    public UUID keyCuentaOrigen;
    public UUID keyCuentaDestino;
    public String descripcion;
    public UUID keyCategoria;
    public double monto;
    public LocalDateTime fecha;

    public MovimientoDto() {
    }

    public MovimientoDto(UUID key, UUID keyCuentaOrigen, UUID keyCuentaDestino, String descripcion,
            UUID keyCategoria, double monto, LocalDateTime fecha) {
        this.key = key;
        this.keyCuentaOrigen = keyCuentaOrigen;
        this.keyCuentaDestino = keyCuentaDestino;
        this.descripcion = descripcion;
        this.keyCategoria = keyCategoria;
        this.monto = monto;
        this.fecha = fecha;
    }

    public MovimientoDto(Movimiento movimiento){
        this.key = movimiento.key;
        this.keyCuentaOrigen = movimiento.keyCuentaOrigen;
        this.keyCuentaDestino = movimiento.keyCuentaDestino;
        this.descripcion = movimiento.descripcion;
        this.keyCategoria = movimiento.KeyCategoria;
        this.monto = movimiento.getMonto().getMonto();
        this.fecha = movimiento.fecha;
    }

}
