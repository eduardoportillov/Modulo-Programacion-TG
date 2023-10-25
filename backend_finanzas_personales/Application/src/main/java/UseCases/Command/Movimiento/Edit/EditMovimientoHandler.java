package UseCases.Command.Movimiento.Edit;

import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.Movimiento;
import Model.ValueObject.Monto;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.IMovimientoRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class EditMovimientoHandler implements RequestHandler<EditMovimientoCommand, String> {
    private IMovimientoRepository _movimientoRepository;

    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public EditMovimientoHandler(IMovimientoRepository _movimientoRepository,
            ICategoriaMovimientoRepository _categoriaMovimientoRepository, ISecurityUtils _securityUtils,
            IUnitOfWork _unitOfWork) {
        this._movimientoRepository = _movimientoRepository;
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EditMovimientoCommand request) throws Exception {

        try {
            _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        Movimiento movimiento = _movimientoRepository.FindByKey(request.movimiento.key);
        if (movimiento == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "El movimiento no existe");
        }

        if (request.movimiento.keyCategoria != null) {
            if (_categoriaMovimientoRepository.FindByKey(request.movimiento.keyCategoria) == null) {
                throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría movimiento no pertenece al usuario");
                // TODO Revisar esta validación
            }
            movimiento.KeyCategoria = request.movimiento.keyCategoria;
        }

        if (request.movimiento.descripcion != null) {
            movimiento.descripcion = request.movimiento.descripcion;
        }

        movimiento.eventEdit(movimiento.getKey(), movimiento.getMonto().getMonto(), request.movimiento.monto);

        movimiento.setMonto(new Monto(request.movimiento.monto));
        _movimientoRepository.Update(movimiento);
        _unitOfWork.commit();

        return "Movimiento editado con exito";
    }

}
