import Context.IWriteDbContext;
import Fourteam.extensions.IServiceCollection;
import Repositories.*;
import Repository.*;
import Security.SecurityUtils;

public class Infraestructure {

  public static void AddInfraestructure() {
    IServiceCollection.AddMediator();
    IServiceCollection.AddScoped(IWriteDbContext.class, Context.MongoDB.WriteDbContext.class);
    IServiceCollection.AddScoped(IUnitOfWork.class, UnitOfWork.class);
    IServiceCollection.AddScoped(IUserRepository.class, UserRepository.class);
    IServiceCollection.AddScoped(ICuentaRepository.class, CuentaRepository.class);
    IServiceCollection.AddScoped(ICategoriaCuentaRepository.class, CategoriaCuentaRepository.class);
    IServiceCollection.AddScoped(ICategoriaMovimientoRepository.class, CategoriaMovimientoRepository.class);
    IServiceCollection.AddScoped(IMovimientoRepository.class, MovimientoRepository.class);
    IServiceCollection.AddScoped(ISecurityUtils.class, SecurityUtils.class);

    Application.AddApplication();
  }
}
