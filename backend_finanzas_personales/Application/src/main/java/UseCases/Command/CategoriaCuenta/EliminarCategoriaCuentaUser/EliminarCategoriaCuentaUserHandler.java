package UseCases.Command.CategoriaCuenta.EliminarCategoriaCuentaUser;

import java.util.UUID;

import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.CategoriaCuenta;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class EliminarCategoriaCuentaUserHandler implements RequestHandler<EliminarCategoriaCuentaUserCommand, String> {
    private ICategoriaCuentaRepository _categoriaCuentaRepository;

    private ISecurityUtils _securityUtils;

    IUnitOfWork _unitOfWork;

    public EliminarCategoriaCuentaUserHandler(ICategoriaCuentaRepository _categoriaCuentaRepository,
            ISecurityUtils _securityUtils, IUnitOfWork _unitOfWork) {
        this._categoriaCuentaRepository = _categoriaCuentaRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EliminarCategoriaCuentaUserCommand request) throws Exception {
        UUID keyUser;
        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        CategoriaCuenta categoriaCuenta = _categoriaCuentaRepository.FindByKey(request.categoriacuenta.getKey());

        if (categoriaCuenta.getKeyUser().equals(keyUser)) {
            _categoriaCuentaRepository.Delete(categoriaCuenta);
            _unitOfWork.commit();
            return "Categoria Cuenta del User Eliminado con éxito";
        } else {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La Categoria Cuenta no pertenece al usuario");
        }
    }

}
