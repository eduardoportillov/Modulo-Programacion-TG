package Repository;

import java.util.List;
import java.util.UUID;

import Context.IWriteDbContext;
import Entities.Movimiento.CategoriaMovimiento;
import Fourteam.db.DbSet;
import Fourteam.db.IDbSet.BooleanFunction;
import Repositories.ICategoriaMovimientoRepository;

public class CategoriaMovimientoRepository implements ICategoriaMovimientoRepository {

    DbSet<CategoriaMovimiento> dbSet;

    public CategoriaMovimientoRepository(IWriteDbContext database) {
        dbSet = database.categoriaMovimiento;
    }

    public BooleanFunction<CategoriaMovimiento> equalKey(UUID key) {
        return obj -> obj.key.equals(key);
    }

    public BooleanFunction<CategoriaMovimiento> equalName(String name) {
        return obj -> obj.getNombre().equals(name);
    }

    @Override
    public void Create(CategoriaMovimiento obj) throws Exception {
        dbSet.Add(obj);
    }

    @Override
    public CategoriaMovimiento FindByKey(UUID key) throws Exception {
        return dbSet.Single(equalKey(key));
    }

    @Override
    public CategoriaMovimiento getByName(String name) throws Exception {
        return dbSet.Single(equalName(name));
    }

    @Override
    public CategoriaMovimiento Delete(CategoriaMovimiento CategoriaMovimiento) throws Exception {
        dbSet.Delete(equalKey(CategoriaMovimiento.key));
        return CategoriaMovimiento;
    }

    @Override
    public CategoriaMovimiento Update(CategoriaMovimiento CategoriaMovimiento) throws Exception {
        dbSet.Update(CategoriaMovimiento, equalKey(CategoriaMovimiento.key));
        return CategoriaMovimiento;
    }

    @Override
    public List<CategoriaMovimiento> GetAll() throws Exception {
        return dbSet.All();
    }

}
