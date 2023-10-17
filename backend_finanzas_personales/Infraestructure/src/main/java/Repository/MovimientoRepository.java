package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import Context.IWriteDbContext;
import Entities.User;
import Entities.Cuenta.Cuenta;
import Entities.Movimiento.Movimiento;
import Fourteam.db.DbSet;
import Fourteam.db.IDbSet.BooleanFunction;
import Repositories.IMovimientoRepository;

public class MovimientoRepository implements IMovimientoRepository {

    DbSet<Movimiento> dbSet;
    DbSet<Cuenta> dbSetCuenta;

    public MovimientoRepository(IWriteDbContext database) {
        dbSet = database.movimiento;
        dbSetCuenta = database.cuenta;
    }

    public BooleanFunction<Movimiento> equalKey(UUID key) {
        return obj -> obj.key.equals(key);
    }

    @Override
    public void Create(Movimiento obj) throws Exception {
        dbSet.Add(obj);
    }

    @Override
    public Movimiento FindByKey(UUID key) throws Exception {
        return dbSet.Single(equalKey(key));
    }

    @Override
    public Movimiento Delete(Movimiento movimiento) throws Exception {
        dbSet.Delete(equalKey(movimiento.key));
        return movimiento;
    }

    @Override
    public Movimiento Update(Movimiento movimiento) throws Exception {
        dbSet.Update(movimiento, equalKey(movimiento.key));
        return movimiento;
    }

    @Override
    public List<Movimiento> GetByCuenta(UUID keyCuenta) throws Exception {
        return dbSet.Filter(obj -> {
            UUID uuid = obj.getKeyCuentaOrigen();
            UUID uuid2 = obj.getKeyCuentaDestino();
            if (uuid != null) {
                if (uuid.equals(keyCuenta)) {
                    return true;
                }
            }

            if (uuid2 != null) {
                if (uuid2.equals(keyCuenta)) {
                    return true;
                }
            }
            return false;
        });
    }

    @Override
    public List<Movimiento> GetByUser(UUID keyUser) throws Exception {
        List<Movimiento> listMov = new ArrayList<>();

        for (Cuenta cuenta : dbSetCuenta.All()) {
            if (cuenta.getKeyUserTitular().equals(keyUser)) {
                listMov.addAll(GetByCuenta(cuenta.key));
            }
        }

        return listMov;

    }

}
