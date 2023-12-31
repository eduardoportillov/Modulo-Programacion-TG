package Controllers;

import java.util.List;
import java.util.UUID;

import Dto.MovimientoDto;
import Dto.UserDto;
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
import UseCases.Command.Movimiento.Create.CreateMovimientoCommand;
import UseCases.Command.Movimiento.Edit.EditMovimientoCommand;
import UseCases.Command.Movimiento.Eliminar.EliminarMovimientoCommand;
import UseCases.Queries.Movimiento.GetByKey.GetMovimientoByKeyQuery;
import UseCases.Queries.Movimiento.GetMovimientoByCuenta.GetMovimientoByCuentaQuery;
import UseCases.Queries.Movimiento.GetMovimientoByUser.GetMovimientoByUserQuery;
import UseCases.Queries.User.GetAll.GetAllUserQuery;

@RestController
@RequestMapping("/movimiento")
public class MovimientosController {

    private Mediator _mediator;

    public MovimientosController(Mediator mediator) {
        this._mediator = mediator;
    }

    // Commands
    @PostMapping("/create")
    public String create(@RequestBody CreateMovimientoCommand movimiento,
            @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
        movimiento.token = auth;
        return (String) _mediator.send(movimiento).data;
    }

    @PutMapping("/{key}")
    public String edit(
            @RequestBody MovimientoDto movimiento,
            @PathVariable EditMovimientoCommand request,
            @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
        request.token = auth;
        request.movimiento.descripcion = movimiento.descripcion;
        request.movimiento.keyCategoria = movimiento.keyCategoria;
        request.movimiento.monto = movimiento.monto;
        return (String) _mediator.send(request).data;
    }

    @DeleteMapping("/{key}")
    public UUID delete(
            @PathVariable EliminarMovimientoCommand request,
            @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
        request.token = auth;
        return (UUID) _mediator.send(request).data;
    }

    // Querys
    @GetMapping("/getbykey/{key}")
    public MovimientoDto getByKey(
            @PathVariable GetMovimientoByKeyQuery request,
            @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
        request.token = auth;
        return (MovimientoDto) _mediator.send(request).data;
    }

    @GetMapping("/getbycuenta/{key}")
    public List<MovimientoDto> getByCuenta(
            @PathVariable GetMovimientoByCuentaQuery request,
            @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
        request.token = auth;
        return (List<MovimientoDto>) _mediator.send(request).data;
    }

    @GetMapping("/getbyuser")
    public List<MovimientoDto> getByUser(
            // @PathVariable GetMovimientoByUserQuery request,
            @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
        // request.token = auth;
        // return (List<MovimientoDto>) _mediator.send(request).data;

        Response<List<MovimientoDto>> lista = _mediator.send(new GetMovimientoByUserQuery(auth));
        return lista.data;
    }
}
