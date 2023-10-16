package Repository;

import java.util.List;
import java.util.UUID;

import Context.IWriteDbContext;
import Entities.Cuenta.Cuenta;
import Fourteam.db.DbSet;
import Fourteam.db.IDbSet.BooleanFunction;
import Repositories.ICuentaRepository;

public class CuentaRepository implements ICuentaRepository {

    DbSet<Cuenta> dbSet;

    public CuentaRepository(IWriteDbContext database) {
        dbSet = database.cuenta;
    }

    public BooleanFunction<Cuenta> equalKey(UUID key) {
        return obj -> obj.key.equals(key);
    }

    @Override
    public void Create(Cuenta obj) throws Exception {
        dbSet.Add(obj);
    }

    @Override
    public Cuenta FindByKey(UUID key) throws Exception {
        return dbSet.Single(equalKey(key));
    }

    @Override
    public Cuenta Delete(Cuenta cuenta) throws Exception {
        dbSet.Delete(equalKey(cuenta.key));
        return cuenta;
    }

    @Override
    public Cuenta Update(Cuenta cuenta) throws Exception {
        dbSet.Update(cuenta, equalKey(cuenta.key));
        return cuenta;
    }

    @Override
    public List<Cuenta> GetAll() throws Exception {
        return dbSet.All();
    }

    @Override
    public List<Cuenta> GetByUser(UUID keyUser) throws Exception {
        return dbSet.Filter(obj -> obj.getKeyUserTitular().equals(keyUser));
    }

}