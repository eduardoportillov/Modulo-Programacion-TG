package UseCases.Command.Cuenta.Edit;

import java.util.UUID;

import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.Cuenta;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ICuentaRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class EditCuentaHandler implements RequestHandler<EditCuentaCommand, String> {
    private ICuentaRepository _cuentaRepository;

    private ICategoriaCuentaRepository _categoriaCuentaRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public EditCuentaHandler(ICuentaRepository _cuentaRepository, ICategoriaCuentaRepository _categoriaCuentaRepository,
            ISecurityUtils _securityUtils, IUnitOfWork _unitOfWork) {
        this._cuentaRepository = _cuentaRepository;
        this._categoriaCuentaRepository = _categoriaCuentaRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EditCuentaCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        Cuenta cuenta = _cuentaRepository.FindByKey(request.cuenta.key);
        if (cuenta == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Cuenta no encontrada");
        }

        if (request.cuenta.nombre != null) {
            cuenta.nombre = request.cuenta.nombre;
        }

        if (request.cuenta.keyUserTitular != null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "No se puede editar el titular");
        }

        if (request.cuenta.keyCategoria != null) {
            if (!_categoriaCuentaRepository.FindByKey(request.cuenta.keyCategoria).getKeyUser().equals(keyUser)) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría cuenta no pertenece al usuario");
            }
            cuenta.keyCategoria = request.cuenta.keyCategoria;
        }

        if (request.cuenta.getMonto() != 0) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "No se puede edita el monto de forma arbitraria");
        }

        _cuentaRepository.Update(cuenta);
        _unitOfWork.commit();

        return "Cuenta editada con exito ";
    }

}
