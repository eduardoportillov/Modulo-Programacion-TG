package Repositories;

import java.util.List;
import java.util.UUID;

import Entities.Movimiento;
import core.IRepository;

public interface IMovimientoRepository extends IRepository<Movimiento, UUID> {
    public Movimiento FindByKey(UUID key) throws Exception;

    public Movimiento Delete(Movimiento movimiento) throws Exception;

    public Movimiento Update(Movimiento movimiento) throws Exception;

    public List<Movimiento> GetByCuenta(UUID keyCuenta) throws Exception;

    public List<Movimiento> GetByUser(UUID keyUser) throws Exception;
}
