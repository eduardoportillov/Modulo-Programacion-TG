package Controllers;

import java.util.List;

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
import UseCases.Command.CategoriaMovimiento.Create.CrearCategoriaMovimientoCommand;
import UseCases.Command.CategoriaMovimiento.CreateCategoriaMovimientoUser.CreateCategoriaMovimientoUserCommand;
import UseCases.Command.CategoriaMovimiento.EditCategoriaMovimientoUser.EditCategoriaMovimientoUserCommand;
import UseCases.Command.CategoriaMovimiento.EliminarCategoriaMovimientoUser.EliminarCategoriaMovimientoUserCommand;
import UseCases.Queries.CategoriaMovimiento.GetAll.GetAllCategoriaMovimientoQuery;
import UseCases.Queries.CategoriaMovimiento.GetAllCategoriaMoviminetoUser.GetAllCategoriaMoviminetoUserQuery;

@RestController
@RequestMapping("/categoriamovimiento")
public class CategoriaMovimientoController {
  private Mediator _mediator;

  public CategoriaMovimientoController(Mediator mediator) {
    this._mediator = mediator;
  }

  @PostMapping("/createglobal")
  public String create(@RequestBody CrearCategoriaMovimientoCommand categoriaMovimiento,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    categoriaMovimiento.token = auth;
    return (String) _mediator.send(categoriaMovimiento).data;
  }

  @GetMapping("/allglobal")
  public List<CategoriaMovimientoDto> getAll(@RequestHeader(value = "Authorization", required = true) String auth)
      throws Exception {
    Response<List<CategoriaMovimientoDto>> lista = _mediator.send(new GetAllCategoriaMovimientoQuery());
    return lista.data;
  }

  // User
  @PostMapping("/createuser")
  public CategoriaMovimientoDto createUser(@RequestBody CreateCategoriaMovimientoUserCommand categoriaMovimiento,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    categoriaMovimiento.token = auth;
    return (CategoriaMovimientoDto) _mediator.send(categoriaMovimiento).data;
  }

  @PutMapping("/edituser/{key}")
  public String edit(
      @RequestBody CategoriaMovimientoDto categoriaMovimiento,
      @PathVariable EditCategoriaMovimientoUserCommand request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    request.categoriaMovimiento.nombre = categoriaMovimiento.nombre;
    return (String) _mediator.send(request).data;
  }

  @DeleteMapping("/{key}")
  public String delete(
      @PathVariable EliminarCategoriaMovimientoUserCommand request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    return (String) _mediator.send(request).data;
  }

  @GetMapping("/getalluser")
  public List<CategoriaMovimientoDto> getAllCategoriaUser(
      @RequestHeader(value = "Authorization", required = true) String auth)
      throws Exception {
    Response<List<CategoriaMovimientoDto>> lista = _mediator.send(new GetAllCategoriaMoviminetoUserQuery(auth));
    return lista.data;
  }

}
