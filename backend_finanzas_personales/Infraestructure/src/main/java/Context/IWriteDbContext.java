package Context;

import Fourteam.db.DbContext;
import Fourteam.db.DbSet;
import Fourteam.db.Exception.DataBaseException;
import Model.CategoriaCuenta;
import Model.CategoriaMovimiento;
import Model.Cuenta;
import Model.Movimiento;
import Model.User;

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
