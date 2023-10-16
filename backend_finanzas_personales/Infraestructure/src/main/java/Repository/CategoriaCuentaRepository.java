package Repository;

import java.util.List;
import java.util.UUID;

import Context.IWriteDbContext;
import Entities.Cuenta.CategoriaCuenta;
import Fourteam.db.DbSet;
import Fourteam.db.IDbSet.BooleanFunction;
import Repositories.ICategoriaCuentaRepository;

public class CategoriaCuentaRepository implements ICategoriaCuentaRepository {

    DbSet<CategoriaCuenta> dbSet;

    public CategoriaCuentaRepository(IWriteDbContext database) {
        dbSet = database.categoriaCuenta;
    }

    public BooleanFunction<CategoriaCuenta> equalKey(UUID key) {
        return obj -> obj.key.equals(key);
    }

    public BooleanFunction<CategoriaCuenta> equalName(String name) {
        return obj -> obj.getNombre().equals(name);
      }

    @Override
    public void Create(CategoriaCuenta obj) throws Exception {
        dbSet.Add(obj);
    }

    @Override
    public CategoriaCuenta FindByKey(UUID key) throws Exception {
        return dbSet.Single(equalKey(key));
    }

    @Override
    public CategoriaCuenta getByName(String name) throws Exception {
        return dbSet.Single(equalName(name));
    }

    @Override
    public CategoriaCuenta Delete(CategoriaCuenta CategoriaCuenta) throws Exception {
        dbSet.Delete(equalKey(CategoriaCuenta.key));
        return CategoriaCuenta;
    }

    @Override
    public CategoriaCuenta Update(CategoriaCuenta CategoriaCuenta) throws Exception {
        dbSet.Update(CategoriaCuenta, equalKey(CategoriaCuenta.key));
        return CategoriaCuenta;
    }

    @Override
    public List<CategoriaCuenta> GetAll() throws Exception {
        return dbSet.All();
    }

}
