package UseCases.Command.CategoriaMovimiento.CreateCategoriaMovimientoUser;

import java.util.UUID;

import Dto.CategoriaMovimientoDto;
import Factories.CategoriaMovimiento.ICategoriaMovimientoFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Model.CategoriaMovimiento;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class CreateCategoriaMovimientoUserHandler
        implements RequestHandler<CreateCategoriaMovimientoUserCommand, CategoriaMovimientoDto> {
    private ICategoriaMovimientoFactory _movimientoCategoriaFactory;
    private ICategoriaMovimientoRepository _cuentaCategoriaRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public CreateCategoriaMovimientoUserHandler(ICategoriaMovimientoFactory _movimientoCategoriaFactory,
            ICategoriaMovimientoRepository _cuentaCategoriaRepository, ISecurityUtils _securityUtils,
            IUnitOfWork _unitOfWork) {
        this._movimientoCategoriaFactory = _movimientoCategoriaFactory;
        this._cuentaCategoriaRepository = _cuentaCategoriaRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public CategoriaMovimientoDto handle(CreateCategoriaMovimientoUserCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);

            CategoriaMovimiento categoriaCuenta = _movimientoCategoriaFactory.Create(request.cm.getNombre(), keyUser);
            _cuentaCategoriaRepository.Create(categoriaCuenta);

            _unitOfWork.commit();

            return new CategoriaMovimientoDto(categoriaCuenta);

        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

    }

}
