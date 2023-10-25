package Repositories;

import java.util.List;
import java.util.UUID;

import Entities.CategoriaCuenta;
import core.IRepository;

public interface ICategoriaCuentaRepository extends IRepository<CategoriaCuenta, UUID> {
    public CategoriaCuenta FindByKey(UUID key) throws Exception;

    public CategoriaCuenta getByName(String name) throws Exception;

    public CategoriaCuenta Delete(CategoriaCuenta CategoriaCuenta) throws Exception;

    public CategoriaCuenta Update(CategoriaCuenta CategoriaCuenta) throws Exception;

    public List<CategoriaCuenta> GetAll() throws Exception;

    public List<CategoriaCuenta> GetAllByKeyUser(UUID keyUser) throws Exception;
}
