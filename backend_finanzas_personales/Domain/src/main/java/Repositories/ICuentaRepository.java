package Repositories;

import java.util.List;
import java.util.UUID;

import Entities.Cuenta.Cuenta;
import core.IRepository;

public interface ICuentaRepository extends IRepository<Cuenta, UUID> {
    public Cuenta FindByKey(UUID key) throws Exception;

    public Cuenta Delete(Cuenta cuenta) throws Exception;

    public Cuenta Update(Cuenta cuenta) throws Exception;

    public List<Cuenta> GetAll() throws Exception;

    public List<Cuenta> GetByUser(UUID keyUser) throws Exception;

}
