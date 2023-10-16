
import Controllers.CategoriaCuentaController;
import Controllers.CategoriaMovimientoController;
import Controllers.CuentaController;
import Controllers.MovimientosController;
import Controllers.UserController;
import Fourteam.config.Config;
import Fourteam.http.Rest;

public class WebApi {
  public static void AddControllers() {
    Rest.addController(UserController.class);
    Rest.addController(CuentaController.class);
    Rest.addController(CategoriaCuentaController.class);
    Rest.addController(MovimientosController.class);
    Rest.addController(CategoriaMovimientoController.class);
    Rest.addController(MovimientosController.class);

    Rest.start(Integer.parseInt(Config.getProperty("http.port")));
  }
}
