package UseCases.Command.CategoriaCuenta.EditCategoriaCuentaUser;

import java.util.UUID;

import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.CategoriaCuenta;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class EditCategoriaCuentaUserHandler implements RequestHandler<EditCategoriaCuentaUserCommand, String> {
    private ICategoriaCuentaRepository _categoriaCuentaRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public EditCategoriaCuentaUserHandler(ICategoriaCuentaRepository _categoriaCuentaRepository,
            ISecurityUtils _securityUtils, IUnitOfWork _unitOfWork) {
        this._categoriaCuentaRepository = _categoriaCuentaRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EditCategoriaCuentaUserCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        CategoriaCuenta categoriaCuenta = _categoriaCuentaRepository.FindByKey(request.categoriacuenta.getKey());

        if (categoriaCuenta.getKeyUser().equals(keyUser)) {
            categoriaCuenta.setNombre(request.categoriacuenta.getNombre());
            _categoriaCuentaRepository.Update(categoriaCuenta);
            _unitOfWork.commit();
            return "Categoria Cuenta del User editada con éxito";
        } else {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La Categoria Cuenta no pertenece al usuario");
        }

    }

}
