package Controllers;

import java.util.List;

import Dto.UserDto;
import Fourteam.http.annotation.*;
import Fourteam.mediator.Mediator;
import Fourteam.mediator.Response;
import UseCases.Command.User.Create.CrearUserCommand;
import UseCases.Command.User.Edit.EditUserCommand;
import UseCases.Queries.User.FindByKey.FindUserByKeyQuery;
import UseCases.Queries.User.GetAll.GetAllUserQuery;
import UseCases.Queries.User.Login.LoginUserQuery;

@RestController
@RequestMapping("/user")
public class UserController {

  private Mediator _mediator;

  public UserController(Mediator mediator) {
    this._mediator = mediator;
  }

  @PostMapping("/registro")
  public String register(@RequestBody CrearUserCommand user) throws Exception {
    return (String) _mediator.send(user).data;
  }

  @PostMapping("/login")
  public String login(@RequestBody LoginUserQuery request) throws Exception {
    return (String) _mediator.send(request).data;
  }

  @PutMapping("/edit/{key}")
  public UserDto edit(
      @RequestBody UserDto user,
      @PathVariable EditUserCommand request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    request.user.email = user.email;
    request.user.password = user.password;
    return (UserDto) _mediator.send(request).data;
  }

  @GetMapping("/all")
  public List<UserDto> getAll(
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    Response<List<UserDto>> lista = _mediator.send(new GetAllUserQuery(auth));
    return lista.data;
  }

  @GetMapping("/{key}")
  public UserDto getByKey(
      @PathVariable FindUserByKeyQuery request,
      @RequestHeader(value = "Authorization", required = true) String auth) throws Exception {
    request.token = auth;
    return (UserDto) _mediator.send(request).data;
  }
}
