package UseCases.Command.CategoriaMovimiento.EliminarCategoriaMovimientoUser;

import java.util.UUID;

import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.CategoriaMovimiento;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class EliminarCategoriaMovimientoUserHandler
        implements RequestHandler<EliminarCategoriaMovimientoUserCommand, String> {
    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;

    private ISecurityUtils _securityUtils;

    IUnitOfWork _unitOfWork;

    public EliminarCategoriaMovimientoUserHandler(ICategoriaMovimientoRepository _categoriaMovimientoRepository,
            ISecurityUtils _securityUtils, IUnitOfWork _unitOfWork) {
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EliminarCategoriaMovimientoUserCommand request) throws Exception {
        UUID keyUser;
        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        CategoriaMovimiento categoriaMovimiento = _categoriaMovimientoRepository
                .FindByKey(request.categoriaMovimiento.getKey());

        if (categoriaMovimiento.getKeyUser().equals(keyUser)) {
            _categoriaMovimientoRepository.Delete(categoriaMovimiento);
            _unitOfWork.commit();
            return "Categoria Movimiento del User Eliminado con éxito";
        } else {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La Categoria Movimiento no pertenece al usuario");
        }

    }

}
