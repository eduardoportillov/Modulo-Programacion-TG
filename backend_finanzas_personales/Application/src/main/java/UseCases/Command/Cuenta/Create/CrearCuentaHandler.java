package UseCases.Command.Cuenta.Create;

import java.util.UUID;

import Dto.CuentaDto;
import Entities.CategoriaCuenta;
import Entities.Cuenta;
import Factories.Cuenta.ICuentaFactory;
import Fourteam.http.HttpStatus;
import Fourteam.http.Exception.HttpException;
import Fourteam.mediator.RequestHandler;
import Repositories.ICategoriaCuentaRepository;
import Repositories.ICategoriaMovimientoRepository;
import Repositories.ICuentaRepository;
import Repositories.ISecurityUtils;
import Repositories.IUnitOfWork;

public class CrearCuentaHandler implements RequestHandler<CrearCuentaCommand, CuentaDto> {
    private ICuentaFactory _cuentaFactory;
    private ICuentaRepository _cuentaRepository;
    private ICategoriaCuentaRepository _categoriaCuentaRepository;

    private ICategoriaMovimientoRepository _categoriaMovimientoRepository;

    private ISecurityUtils _securityUtils;

    private IUnitOfWork _unitOfWork;

    public CrearCuentaHandler(ICuentaFactory _cuentaFactory, ICuentaRepository _cuentaRepository,
            ICategoriaCuentaRepository _categoriaCuentaRepository,
            ICategoriaMovimientoRepository _categoriaMovimientoRepository, ISecurityUtils _securityUtils,
            IUnitOfWork _unitOfWork) {
        this._cuentaFactory = _cuentaFactory;
        this._cuentaRepository = _cuentaRepository;
        this._categoriaCuentaRepository = _categoriaCuentaRepository;
        this._categoriaMovimientoRepository = _categoriaMovimientoRepository;
        this._securityUtils = _securityUtils;
        this._unitOfWork = _unitOfWork;
    }

    @Override
    public CuentaDto handle(CrearCuentaCommand request) throws Exception {
        UUID keyUser;

        try {
            keyUser = _securityUtils.decodeToken(request.token);
        } catch (Exception e) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Token Invalido o vencido");
        }

        CategoriaCuenta categoriaCuenta = _categoriaCuentaRepository.FindByKey(request.data.keyCategoria);
        if (categoriaCuenta == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría cuenta no existe");
        }

        if (categoriaCuenta.getKeyUser() == null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría cuenta es una cuenta global no de usuario");
        }

        if (!categoriaCuenta.getKeyUser().equals(keyUser)) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "La categoría cuenta no pertenece al usuario");
        }

        Cuenta cuenta = _cuentaFactory.Create(request.data.nombre, keyUser, categoriaCuenta.getKey());

        UUID keyMovimiento = _categoriaMovimientoRepository.GetAllByKeyUser(keyUser).get(0).getKey();
        cuenta.eventCreado(cuenta.key, keyMovimiento, request.data.getMonto());

        _cuentaRepository.Create(cuenta);

        _unitOfWork.commit();

        return new CuentaDto(cuenta);
    }

}
