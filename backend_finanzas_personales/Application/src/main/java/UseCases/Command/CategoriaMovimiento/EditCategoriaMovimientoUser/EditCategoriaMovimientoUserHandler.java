package UseCases.Command.CategoriaMovimiento.EditCategoriaMovimientoUser;

import java.util.UUID;

import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.CategoriaMovimiento;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class EditCategoriaMovimientoUserHandler implements RequestHandler<EditCategoriaMovimientoUserCommand, String> {
    private ICategoriaMovimientoRepository _movimientoCategoriaRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    
    public EditCategoriaMovimientoUserHandler(ICategoriaMovimientoRepository _movimientoCategoriaRepository,
            ISecurityUtils _securityUtils, IUnitOfWork _unitOfWork) {
        this._movimientoCategoriaRepository = _movimientoCategoriaRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }


    @Override
    public String handle(EditCategoriaMovimientoUserCommand request) throws Exception {
        UUID keyUser;
        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        CategoriaMovimiento categoriaMovimiento = _movimientoCategoriaRepository
                .FindByKey(request.categoriaMovimiento.getKey());

        if (categoriaMovimiento.getKeyUser().equals(keyUser)) {
            categoriaMovimiento.setNombre(request.categoriaMovimiento.getNombre());
            _movimientoCategoriaRepository.Update(categoriaMovimiento);
            _unitOfWork.commit();
            return "Categoria Movimiento del User editada con éxito";
        } else {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La Categoria Movimiento no pertenece al usuario");
        }
    }

}
