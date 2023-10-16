package UseCases.Command.CategoriaMovimiento.Create;

import java.util.Arrays;
import java.util.List;

import Entities.Cuenta.CategoriaMovimiento;
import Factories.CategoriaMovimiento.ICategoriaMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.IUnitOfWork;

public class CrearCategoriaMovimientoHandler implements RequestHandler<CrearCategoriaMovimientoCommand, String> {
    private ICategoriaMovimientoFactory _cuentaMovimientoFactory;
    private ICategoriaMovimientoRepository _cuentaMovimientoRepository;

    private IUnitOfWork _unitOfWork;

    public CrearCategoriaMovimientoHandler(ICategoriaMovimientoFactory _cuentaMovimientoFactory,
            ICategoriaMovimientoRepository _cuentaMovimientoRepository, IUnitOfWork _unitOfWork) {
        this._cuentaMovimientoFactory = _cuentaMovimientoFactory;
        this._cuentaMovimientoRepository = _cuentaMovimientoRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(CrearCategoriaMovimientoCommand request) throws Exception {
        if (request.data.isCreateCategoryDefault()) {
            List<String> categoryNames = Arrays.asList("Default", "Comida y Bebida", "Compras", "Viviendas",
                    "Trasporte");

            for (String categoryName : categoryNames) {
                if (_cuentaMovimientoRepository.getByName(categoryName) == null) {
                    CategoriaMovimiento categoriaCuenta = _cuentaMovimientoFactory.Create(categoryName);
                    _cuentaMovimientoRepository.Create(categoriaCuenta);
                } else {
                    throw new HttpException(HttpStatus.BAD_REQUEST, "Alguna de las categorias pasadas ya existen");
                }
            }
            _unitOfWork.commit();

            return "Categorías globale creada con éxito";
        }

        CategoriaMovimiento categoriaCuenta = _cuentaMovimientoFactory.Create(request.data.getNombre());
        _cuentaMovimientoRepository.Create(categoriaCuenta);
        _unitOfWork.commit();
        return "categoria creada con éxito";
    }

}
