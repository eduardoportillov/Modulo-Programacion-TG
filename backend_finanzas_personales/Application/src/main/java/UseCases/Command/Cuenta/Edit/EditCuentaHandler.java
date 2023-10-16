package UseCases.Command.Cuenta.Edit;

import Entities.User;
import Entities.Cuenta.Cuenta;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.IUnitOfWork;

public class EditCuentaHandler implements RequestHandler<EditCuentaCommand, String> {
    private ICuentaRepository _cuentaRepository;
    private IUnitOfWork _unitOfWork;

    public EditCuentaHandler(ICuentaRepository _cuentaRepository, IUnitOfWork _unitOfWork) {
        this._cuentaRepository = _cuentaRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public String handle(EditCuentaCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
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
            if (user.isCategoriaCuentaUser(request.cuenta.keyCategoria)) {
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
