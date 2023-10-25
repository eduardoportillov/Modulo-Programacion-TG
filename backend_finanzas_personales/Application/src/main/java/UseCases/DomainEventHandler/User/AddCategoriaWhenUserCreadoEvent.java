package UseCases.DomainEventHandler.User;

import java.util.List;

import DomainEvents.User.UserCreado;
import Entities.CategoriaCuenta;
import Entities.CategoriaMovimiento;
import Entities.Cuenta;
import Entities.User;
import Factories.CategoriaCuenta.ICategoriaCuentaFactory;
import Factories.CategoriaMovimiento.ICategoriaMovimientoFactory;
import Factories.Cuenta.ICuentaFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.NotificationHandler;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.ICuentaRepository;
import Repositories.IUserRepository;
import core.ConfirmedDomainEvent;

public class AddCategoriaWhenUserCreadoEvent implements NotificationHandler<ConfirmedDomainEvent<UserCreado>> {
    private IUserRepository _userRepository;

    private ICuentaRepository _cuentaRepository;
    private ICuentaFactory _cuentaFactory;

    private ICategoriaCuentaRepository _categoriaCuentaRepository;
    private ICategoriaCuentaFactory _categoriaCuentaFactory;

    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;
    private ICategoriaMovimientoFactory _categoriaMovimientoFactory;

    public AddCategoriaWhenUserCreadoEvent(IUserRepository _userRepository, ICuentaRepository _cuentaRepository,
            ICuentaFactory _cuentaFactory, ICategoriaCuentaRepository _categoriaCuentaRepository,
            ICategoriaCuentaFactory _categoriaCuentaFactory,
            ICategoriaMovimientoRepository _categoriaMovimientoRepository,
            ICategoriaMovimientoFactory _categoriaMovimientoFactory) {
        this._userRepository = _userRepository;
        this._cuentaRepository = _cuentaRepository;
        this._cuentaFactory = _cuentaFactory;
        this._categoriaCuentaRepository = _categoriaCuentaRepository;
        this._categoriaCuentaFactory = _categoriaCuentaFactory;
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
        this._categoriaMovimientoFactory = _categoriaMovimientoFactory;
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
                if (categoria.getKeyUser() == null) {
                    CategoriaCuenta categoriaCuenta = _categoriaCuentaFactory.Create(categoria.getNombre(), user.key,
                            categoria.getColor());
                    _categoriaCuentaRepository.Create(categoriaCuenta);
                }

            }

            List<CategoriaMovimiento> listMovimientos = _categoriaMovimientoRepository.GetAll();

            for (CategoriaMovimiento categoria : listMovimientos) {
                if (categoria.getKeyUser() == null) {
                    CategoriaMovimiento categoriaMovimiento = _categoriaMovimientoFactory.Create(categoria.getNombre(),
                            user.key);
                    _categoriaMovimientoRepository.Create(categoriaMovimiento);
                }
            }

            Cuenta cuenta = _cuentaFactory.Create("Cuenta Default", user.key,
                    _categoriaCuentaRepository.GetAllByKeyUser(user.key).get(0).getKey());

            _cuentaRepository.Create(cuenta);

            _userRepository.Update(user);

        } catch (Exception e) {
            new Exception(e.getMessage());
        }
    }
}
