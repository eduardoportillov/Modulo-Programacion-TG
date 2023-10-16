package UseCases.Command.Cuenta.Create;

import Dto.CuentaDto;
import Entities.User;
import Entities.Cuenta.Cuenta;
import Factories.Cuenta.ICuentaFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICuentaRepository;
import Repositories.IUnitOfWork;

public class CrearCuentaHandler implements RequestHandler<CrearCuentaCommand, CuentaDto> {
    private ICuentaFactory _cuentaFactory;
    private ICuentaRepository _cuentaRepository;
    private IUnitOfWork _unitOfWork;

    public CrearCuentaHandler(
            ICuentaFactory cuentaFactory,
            ICuentaRepository cuentaRepository,
            IUnitOfWork _unitOfWork) {
        this._cuentaFactory = cuentaFactory;
        this._cuentaRepository = cuentaRepository;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public CuentaDto handle(CrearCuentaCommand request) throws Exception {
        User user;

        try {
            user = User.decodeTokenWithUser(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        if (user.isCategoriaCuentaUser(request.data.keyCategoria)) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría cuenta no pertenece al usuario");
        }

        Cuenta cuenta = _cuentaFactory.Create(request.data.nombre, user.key, request.data.keyCategoria);
        
        cuenta.eventCreado(cuenta.key, user.getCategoriaMovimientoUser().get(1).key ,request.data.getMonto());
        
        _cuentaRepository.Create(cuenta);

        _unitOfWork.commit();

        return new CuentaDto(cuenta);
    }

}
