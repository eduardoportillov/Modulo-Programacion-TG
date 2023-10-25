package UseCases.Command.CategoriaCuenta.Create;

import java.util.Arrays;
import java.util.List;
import Entities.CategoriaCuenta;
import Factories.CategoriaCuenta.ICategoriaCuentaFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class CrearCategoriaCuentaHandler implements RequestHandler<CrearCategoriaCuentaCommand, String> {
    private ICategoriaCuentaFactory _cuentaCategoriaFactory;
    private ICategoriaCuentaRepository _cuentaCuentaRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public CrearCategoriaCuentaHandler(ICategoriaCuentaFactory _cuentaCategoriaFactory,
            ICategoriaCuentaRepository _cuentaCuentaRepository, ISecurityUtils _securityUtils,
            IUnitOfWork _unitOfWork) {
        this._cuentaCategoriaFactory = _cuentaCategoriaFactory;
        this._cuentaCuentaRepository = _cuentaCuentaRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(CrearCategoriaCuentaCommand request) throws Exception {
        
        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        List<String> categoryNames = Arrays.asList("General", "Efectivo", "Tarjeta de Credito", "Caja de ahorro");


        for (String categoryName : categoryNames) {
            if (_cuentaCuentaRepository.getByName(categoryName) == null) {
                CategoriaCuenta categoriaCuenta = _cuentaCategoriaFactory.Create(categoryName, null, CategoriaCuenta.generarColorPastelAleatorio());
                _cuentaCuentaRepository.Create(categoriaCuenta);
            } else {
                throw new HttpException(HttpStatus.BAD_REQUEST, "Alguna de las categorias pasadas ya existen");
            }
        }
        _unitOfWork.commit();

        return "Categorías globales creada con éxito";

    }

}
