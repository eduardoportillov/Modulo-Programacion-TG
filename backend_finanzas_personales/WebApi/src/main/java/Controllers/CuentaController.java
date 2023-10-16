package Controllers;

import java.util.List;
import java.util.UUID;

import com.rabbitmq.client.Command;

import Dto.CuentaDto;
import Fourteam.http.annotation.*;
import Fourteam.mediator.Mediator;
import Fourteam.mediator.Response;
import UseCases.Command.Cuenta.AddMonto.AddMontoCuentaCommand;
import UseCases.Command.Cuenta.Create.CrearCuentaCommand;
import UseCases.Command.Cuenta.Edit.EditCuentaCommand;
import UseCases.Command.Cuenta.Eliminar.EliminarCuentaCommand;
import UseCases.Command.Cuenta.Retirar.RetirarMontoCuentaCommand;
import UseCases.Queries.Cuenta.GetAll.GetAllCuentaQuery;
import UseCases.Queries.Cuenta.GetByKey.GetCuentaByKeyQuery;
import UseCases.Queries.Cuenta.GetByToken.GetCuentasByTokenQuery;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

  private Mediator _mediator;

  public CuentaController(Mediator mediator) {
    this._mediator = mediator;
  }

  // Commands
  @PostMapping("/create")
  public CuentaDto create(
      @RequestBody CrearCuentaCommand cuenta,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    cuenta.token = auth;
    return (CuentaDto) _mediator.send(cuenta).data;
  }

  @PutMapping("/{key}")
  public String edit(
      @RequestBody CuentaDto cuenta,
      @PathVariable EditCuentaCommand request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    request.cuenta.nombre = cuenta.nombre;
    request.cuenta.keyCategoria = cuenta.keyCategoria;
    return (String) _mediator.send(request).data;
  }

  @PostMapping("/addmonto")
  public String addMonto(
      @RequestBody AddMontoCuentaCommand cuenta,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    cuenta.token = auth;
    return (String) _mediator.send(cuenta).data;
  }

  @PostMapping("/retirarmonto")
  public String retirar(
      @RequestBody RetirarMontoCuentaCommand cuenta,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    cuenta.token = auth;
    return (String) _mediator.send(cuenta).data;
  }

  @DeleteMapping("/{key}")
  public UUID delete(
      @PathVariable EliminarCuentaCommand request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    return (UUID) _mediator.send(request).data;
  }

  // Querys
  @GetMapping("/all")
  public List<CuentaDto> getAll(
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    Response<List<CuentaDto>> lista = _mediator.send(new GetAllCuentaQuery(auth));
    return lista.data;
  }

  @GetMapping("/bytoken")
  public List<CuentaDto> getCuentasByToken(
      @RequestHeader(value = "Authorization", required = true) String auth)
      throws Exception {
    Response<List<CuentaDto>> lista = _mediator.send(new GetCuentasByTokenQuery(auth));
    return lista.data;
  }

  @GetMapping("/getbykey/{key}")
  public CuentaDto getByKey(
      @PathVariable GetCuentaByKeyQuery request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    return (CuentaDto) _mediator.send(request).data;
  }

}
