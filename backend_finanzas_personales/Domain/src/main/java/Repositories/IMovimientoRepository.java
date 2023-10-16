package Repositories;

import java.util.List;
import java.util.UUID;

import Entities.Cuenta.Movimiento;
import core.IRepository;

public interface IMovimientoRepository extends IRepository<Movimiento, UUID> {
    public Movimiento FindByKey(UUID key) throws Exception;

    public Movimiento Delete(Movimiento movimiento) throws Exception;

    public Movimiento Update(Movimiento movimiento) throws Exception;

    public List<Movimiento> GetByCuenta(UUID keyCuenta) throws Exception;

}
