package Context;

import Entities.User;
import Entities.Cuenta.CategoriaCuenta;
import Entities.Cuenta.CategoriaMovimiento;
import Entities.Cuenta.Cuenta;
import Entities.Cuenta.Movimiento;
import Fourteam.db.DbContext;
import Fourteam.db.DbSet;
import Fourteam.db.Exception.DataBaseException;

public abstract class IWriteDbContext extends DbContext {

  public IWriteDbContext(Class dbContextClass) throws DataBaseException {
    super(dbContextClass);
  }

  public DbSet<User> user;
  public DbSet<Cuenta> cuenta;
  public DbSet<CategoriaCuenta> categoriaCuenta;
  public DbSet<Movimiento> movimiento;
  public DbSet<CategoriaMovimiento> categoriaMovimiento;
}
