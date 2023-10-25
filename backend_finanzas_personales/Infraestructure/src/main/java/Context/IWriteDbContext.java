package Context;

import Entities.CategoriaCuenta;
import Entities.CategoriaMovimiento;
import Entities.Cuenta;
import Entities.Movimiento;
import Entities.User;
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
