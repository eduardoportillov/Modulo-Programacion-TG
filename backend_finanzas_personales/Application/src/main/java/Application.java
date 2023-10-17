
import Factories.CategoriaCuenta.CategoriaCuentaFactory;
import Factories.CategoriaCuenta.ICategoriaCuentaFactory;
import Factories.CategoriaMovimiento.CategoriaMovimientoFactory;
import Factories.CategoriaMovimiento.ICategoriaMovimientoFactory;
import Factories.Cuenta.CuentaFactory;
import Factories.Cuenta.ICuentaFactory;
import Factories.Movimiento.IMovimientoFactory;
import Factories.Movimiento.MovimientoFactory;
import Factories.User.IUserFactory;
import Factories.User.UserFactory;
import Fourteam.extensions.IServiceCollection;
import Fourteam.mediator.IMediator;
import UseCases.Command.CategoriaCuenta.Create.CrearCategoriaCuentaHandler;
import UseCases.Command.CategoriaCuenta.CreateCategoriaCuentaUser.CreateCategoriaCuentaUserHandler;
import UseCases.Command.CategoriaCuenta.EditCategoriaCuentaUser.EditCategoriaCuentaUserHandler;
import UseCases.Command.CategoriaCuenta.EliminarCategoriaCuentaUser.EliminarCategoriaCuentaUserHandler;
import UseCases.Command.CategoriaMovimiento.Create.CrearCategoriaMovimientoHandler;
import UseCases.Command.CategoriaMovimiento.CreateCategoriaMovimientoUser.CreateCategoriaMovimientoUserHandler;
import UseCases.Command.CategoriaMovimiento.EditCategoriaMovimientoUser.EditCategoriaMovimientoUserHandler;
import UseCases.Command.CategoriaMovimiento.EliminarCategoriaMovimientoUser.EliminarCategoriaMovimientoUserHandler;
import UseCases.Command.Cuenta.AddMonto.AddMontoCuentaHandler;
import UseCases.Command.Cuenta.Create.CrearCuentaHandler;
import UseCases.Command.Cuenta.Edit.EditCuentaHandler;
import UseCases.Command.Cuenta.Eliminar.EliminarCuentahandler;
import UseCases.Command.Cuenta.RetirarMonto.RetirarMontoCuentaHandler;
import UseCases.Command.Movimiento.Create.CreateMovimientoHandler;
import UseCases.Command.Movimiento.Edit.EditMovimientoHandler;
import UseCases.Command.Movimiento.Eliminar.EliminarMovimientoHandler;
import UseCases.Command.User.Create.CrearUserHandler;
import UseCases.Command.User.Edit.EditUserHandler;
import UseCases.DomainEventHandler.Cuenta.AddMovimientoEventWhenCuentaCreada;
import UseCases.DomainEventHandler.User.AddCategoriaEventWhenUserCreado;
import UseCases.Queries.CategoriaCuenta.GetAll.GetAllCategoriaCuentaHandler;
import UseCases.Queries.CategoriaCuenta.GetAllCategoriaCuentaUser.GetAllCategoriaCuentaUserHandler;
import UseCases.Queries.CategoriaMovimiento.GetAll.GetAllCategoriaMovimientoHandler;
import UseCases.Queries.CategoriaMovimiento.GetAllCategoriaMoviminetoUser.GetAllCategoriaMoviminetoUserHandler;
import UseCases.Queries.Cuenta.GetAll.GetAllCuentaHandler;
import UseCases.Queries.Cuenta.GetByKey.GetCuentaByKeyHandler;
import UseCases.Queries.Cuenta.GetByToken.GetCuentasByToken;
import UseCases.Queries.Movimiento.GetByKey.GetMovimientoByKeyHandler;
import UseCases.Queries.Movimiento.GetMovimientoByCuenta.GetMovimientoByCuentaHandler;
import UseCases.Queries.Movimiento.GetMovimientoByUser.GetMovimientoByUserHandler;
import UseCases.Queries.User.FindByKey.FindUserByKeyHandler;
import UseCases.Queries.User.GetAll.GetAllUserHandler;
import UseCases.Queries.User.Login.LoginUserHandler;

public class Application {

  public static void AddApplication() {
    // User
    IMediator.registerHandler(CrearUserHandler.class);
    IMediator.registerHandler(EditUserHandler.class);
    IMediator.registerHandler(LoginUserHandler.class);
    IMediator.registerHandler(GetAllUserHandler.class);
    IMediator.registerHandler(FindUserByKeyHandler.class);

    // Cuenta
    IMediator.registerHandler(CrearCuentaHandler.class);
    IMediator.registerHandler(EditCuentaHandler.class);
    IMediator.registerHandler(AddMontoCuentaHandler.class);
    IMediator.registerHandler(EliminarCuentahandler.class);
    IMediator.registerHandler(GetAllCuentaHandler.class);
    IMediator.registerHandler(GetCuentaByKeyHandler.class);
    IMediator.registerHandler(GetCuentasByToken.class);

    // Movimiento
    IMediator.registerHandler(CreateMovimientoHandler.class);
    IMediator.registerHandler(EditMovimientoHandler.class);
    IMediator.registerHandler(GetMovimientoByCuentaHandler.class);
    IMediator.registerHandler(GetMovimientoByUserHandler.class);
    IMediator.registerHandler(EliminarMovimientoHandler.class);

    // Categoria Cuenta
    IMediator.registerHandler(CrearCategoriaCuentaHandler.class);
    IMediator.registerHandler(GetAllCategoriaCuentaHandler.class);

    IMediator.registerHandler(GetAllCategoriaCuentaUserHandler.class);
    IMediator.registerHandler(CreateCategoriaCuentaUserHandler.class);
    IMediator.registerHandler(EditCategoriaCuentaUserHandler.class);
    IMediator.registerHandler(EliminarCategoriaCuentaUserHandler.class);
    IMediator.registerHandler(RetirarMontoCuentaHandler.class);

    // Categoria Movimiento
    IMediator.registerHandler(CrearCategoriaMovimientoHandler.class);
    IMediator.registerHandler(GetAllCategoriaMovimientoHandler.class);
    IMediator.registerHandler(CreateCategoriaMovimientoUserHandler.class);
    IMediator.registerHandler(GetAllCategoriaMoviminetoUserHandler.class);
    IMediator.registerHandler(GetMovimientoByKeyHandler.class);
    IMediator.registerHandler(EditCategoriaMovimientoUserHandler.class);
    IMediator.registerHandler(EliminarCategoriaMovimientoUserHandler.class);

    // Domain Events
    IMediator.registerHandler(AddCategoriaEventWhenUserCreado.class);
    IMediator.registerHandler(AddMovimientoEventWhenCuentaCreada.class);

    IServiceCollection.AddTransient(IUserFactory.class, UserFactory.class);
    IServiceCollection.AddTransient(ICuentaFactory.class, CuentaFactory.class);
    IServiceCollection.AddTransient(ICategoriaCuentaFactory.class, CategoriaCuentaFactory.class);
    IServiceCollection.AddTransient(ICategoriaMovimientoFactory.class, CategoriaMovimientoFactory.class);
    IServiceCollection.AddTransient(IMovimientoFactory.class, MovimientoFactory.class);

    Domain.addDomain();
  }
}
