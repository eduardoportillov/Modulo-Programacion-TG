package UseCases.DomainEventHandler.User;

import java.util.List;

import DomainEvents.UserCreado;
import Entities.User;
import Entities.Cuenta.CategoriaCuenta;
import Entities.Cuenta.Cuenta;
import Entities.Movimiento.CategoriaMovimiento;
import Factories.Cuenta.ICuentaFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.NotificationHandler;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.ICuentaRepository;
import Repositories.IUnitOfWork;
import Repositories.IUserRepository;
import core.ConfirmedDomainEvent;

public class AddCategoriaEventWhenUserCreado implements NotificationHandler<ConfirmedDomainEvent<UserCreado>> {
    private IUserRepository _userRepository;
    private ICuentaRepository _cuentaRepository;
    private ICuentaFactory _cuentaFactory;
    private ICategoriaCuentaRepository _categoriaCuentaRepository;
    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;

    public AddCategoriaEventWhenUserCreado(IUserRepository _userRepository, ICuentaRepository _cuentaRepository,
            ICuentaFactory _cuentaFactory, ICategoriaCuentaRepository _categoriaCuentaRepository,
            ICategoriaMovimientoRepository _categoriaMovimientoRepository, IUnitOfWork _unitOfWork) {
        this._userRepository = _userRepository;
        this._cuentaRepository = _cuentaRepository;
        this._cuentaFactory = _cuentaFactory;
        this._categoriaCuentaRepository = _categoriaCuentaRepository;
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
    }

    @Override
    public void handle(ConfirmedDomainEvent<UserCreado> notification) {
        ConfirmedDomainEvent event = (ConfirmedDomainEvent) notification;
        UserCreado userEvent = (UserCreado) event.DomainEvent;

        try {
            User user = _userRepository.FindByKey(userEvent.getKeyUser());

            if (user == null) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Usuario no encontrado");
            }

            List<CategoriaCuenta> listCategoria = _categoriaCuentaRepository.GetAll();

            for (CategoriaCuenta categoria : listCategoria) {
                user.addCategoriaCuentaUser(categoria);
            }

            List<CategoriaMovimiento> listMovimientos = _categoriaMovimientoRepository.GetAll();

            for (CategoriaMovimiento categoria : listMovimientos) {
                user.addCategoriaMovimientoUser(categoria);
            }

            Cuenta cuenta = _cuentaFactory.Create("Cuenta Default", user.key, listCategoria.get(0).getKey());

            _cuentaRepository.Create(cuenta);

            _userRepository.Update(user);

        } catch (Exception e) {
            new Exception(e.getMessage());
        }
    }
}
