package UseCases.Command.CategoriaCuenta.Create;

import java.util.Arrays;
import java.util.List;

import Entities.User;
import Entities.Cuenta.CategoriaCuenta;
import Factories.CategoriaCuenta.ICategoriaCuentaFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICategoriaCuentaRepository;
import Repositories.IUnitOfWork;

public class CrearCategoriaCuentaHandler implements RequestHandler<CrearCategoriaCuentaCommand, String> {
    private ICategoriaCuentaFactory _cuentaCategoriaFactory;
    private ICategoriaCuentaRepository _cuentaCuentaRepository;

    private IUnitOfWork _unitOfWork;

    public CrearCategoriaCuentaHandler(
            ICategoriaCuentaFactory cuentaCategoriaFactory,
            ICategoriaCuentaRepository cuentaCuentaRepository,
            IUnitOfWork _unitOfWork) {
        this._cuentaCategoriaFactory = cuentaCategoriaFactory;
        this._cuentaCuentaRepository = cuentaCuentaRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(CrearCategoriaCuentaCommand request) throws Exception {

        try {
            User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        if (request.data.isCreateCategoryDefault()) {
            List<String> categoryNames = Arrays.asList("General", "Efectivo", "Tarjeta de Credito", "Caja de ahorro");

            for (String categoryName : categoryNames) {
                if (_cuentaCuentaRepository.getByName(categoryName) == null) {
                    CategoriaCuenta categoriaCuenta = _cuentaCategoriaFactory.Create(categoryName);
                    _cuentaCuentaRepository.Create(categoriaCuenta);
                } else {
                    throw new HttpException(HttpStatus.BAD_REQUEST, "Alguna de las categorias pasadas ya existen");
                }
            }
            _unitOfWork.commit();

            return "Categorías globale creada con éxito";
        }

        CategoriaCuenta categoriaCuenta = _cuentaCategoriaFactory.Create(request.data.getNombre());
        _cuentaCuentaRepository.Create(categoriaCuenta);
        _unitOfWork.commit();
        return "categoria creada con éxito";
    }

}
