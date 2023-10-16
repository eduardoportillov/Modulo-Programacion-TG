package Controllers;

import java.util.List;
import Dto.CategoriaCuentaDto;
import Dto.CategoriaMovimientoDto;
import Fourteam.http.annotation.DeleteMapping;
import Fourteam.http.annotation.GetMapping;
import Fourteam.http.annotation.PathVariable;
import Fourteam.http.annotation.PostMapping;
import Fourteam.http.annotation.PutMapping;
import Fourteam.http.annotation.RequestBody;
import Fourteam.http.annotation.RequestHeader;
import Fourteam.http.annotation.RequestMapping;
import Fourteam.http.annotation.RestController;
import Fourteam.mediator.Mediator;
import Fourteam.mediator.Response;
import UseCases.Command.CategoriaCuenta.Create.CrearCategoriaCuentaCommand;
import UseCases.Command.CategoriaCuenta.CreateCategoriaCuentaUser.CreateCategoriaCuentaUserCommand;
import UseCases.Command.CategoriaCuenta.EditCategoriaCuentaUser.EditCategoriaCuentaUserCommand;
import UseCases.Command.CategoriaCuenta.EliminarCategoriaCuentaUser.EliminarCategoriaCuentaUserCommand;
import UseCases.Queries.CategoriaCuenta.GetAll.GetAllCategoriaCuentaQuery;
import UseCases.Queries.CategoriaCuenta.GetAllCategoriaCuentaUser.GetAllCategoriaCuentaUserQuery;

@RestController
@RequestMapping("/categoriacuenta")
public class CategoriaCuentaController {
  private Mediator _mediator;

  public CategoriaCuentaController(Mediator mediator) {
    this._mediator = mediator;
  }

  @PostMapping("/createglobal")
  public String create(@RequestBody CrearCategoriaCuentaCommand categoriaCuenta,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    categoriaCuenta.token = auth;
    return (String) _mediator.send(categoriaCuenta).data;
  }

  @GetMapping("/allglobal")
  public List<CategoriaCuentaDto> getAll(@RequestHeader(value = "Authorization", required = true) String auth)
      throws Exception {
    Response<List<CategoriaCuentaDto>> lista = _mediator.send(new GetAllCategoriaCuentaQuery());
    return lista.data;
  }

  // User
  @PostMapping("/createuser")
  public CategoriaCuentaDto createUser(@RequestBody CreateCategoriaCuentaUserCommand categoriaCuenta,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    categoriaCuenta.token = auth;
    return (CategoriaCuentaDto) _mediator.send(categoriaCuenta).data;
  }

  @PutMapping("/edituser/{key}")
  public String edit(
      @RequestBody CategoriaCuentaDto categoriacuenta,
      @PathVariable EditCategoriaCuentaUserCommand request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    request.categoriacuenta.nombre = categoriacuenta.nombre;
    return (String) _mediator.send(request).data;
  }

  @DeleteMapping("/{key}")
  public String delete(
      @PathVariable EliminarCategoriaCuentaUserCommand request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    return (String) _mediator.send(request).data;
  }

  @GetMapping("/getalluser")
  public List<CategoriaMovimientoDto> getAllCategoriaUser(
      @RequestHeader(value = "Authorization", required = true) String auth)
      throws Exception {
    Response<List<CategoriaMovimientoDto>> lista = _mediator.send(new GetAllCategoriaCuentaUserQuery(auth));
    return lista.data;
  }

}
