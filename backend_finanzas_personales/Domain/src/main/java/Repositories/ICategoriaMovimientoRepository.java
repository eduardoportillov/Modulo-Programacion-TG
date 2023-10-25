package Repositories;

import java.util.List;
import java.util.UUID;

import Entities.CategoriaMovimiento;
import core.IRepository;

public interface ICategoriaMovimientoRepository extends IRepository<CategoriaMovimiento, UUID> {
    public CategoriaMovimiento FindByKey(UUID key) throws Exception;

    public CategoriaMovimiento getByName(String name) throws Exception;

    public CategoriaMovimiento Delete(CategoriaMovimiento CategoriaMovimiento) throws Exception;

    public CategoriaMovimiento Update(CategoriaMovimiento CategoriaMovimiento) throws Exception;

    public List<CategoriaMovimiento> GetAll() throws Exception;

    public List<CategoriaMovimiento> GetAllByKeyUser(UUID keyUser) throws Exception;
}
