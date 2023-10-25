package UseCases.Command.CategoriaCuenta.CreateCategoriaCuentaUser;

import java.util.UUID;

import Dto.CategoriaCuentaDto;
import Entities.CategoriaCuenta;
import Factories.CategoriaCuenta.ICategoriaCuentaFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class CreateCategoriaCuentaUserHandler
        implements RequestHandler<CreateCategoriaCuentaUserCommand, CategoriaCuentaDto> {
    // private IUserRepository _userRepository;
    private ICategoriaCuentaFactory _cuentaCategoriaFactory;
    private ICategoriaCuentaRepository _cuentaCuentaRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public CreateCategoriaCuentaUserHandler(ICategoriaCuentaFactory _cuentaCategoriaFactory,
            ICategoriaCuentaRepository _cuentaCuentaRepository, ISecurityUtils _securityUtils,
            IUnitOfWork _unitOfWork) {
        this._cuentaCategoriaFactory = _cuentaCategoriaFactory;
        this._cuentaCuentaRepository = _cuentaCuentaRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public CategoriaCuentaDto handle(CreateCategoriaCuentaUserCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);

            CategoriaCuenta categoriaCuenta = _cuentaCategoriaFactory.Create(request.cc.getNombre(), keyUser, CategoriaCuenta.generarColorPastelAleatorio());

            _cuentaCuentaRepository.Create(categoriaCuenta);

            _unitOfWork.commit();

            return new CategoriaCuentaDto(categoriaCuenta);

        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

    }

}
