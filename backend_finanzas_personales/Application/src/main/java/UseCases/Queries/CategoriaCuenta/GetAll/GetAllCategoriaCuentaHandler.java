package UseCases.Queries.CategoriaCuenta.GetAll;

import java.util.ArrayList;
import java.util.List;

import Dto.CategoriaCuentaDto;
import Entities.Cuenta.CategoriaCuenta;
import Fourteam.mediator.RequestHandler;
import Repositories.ICategoriaCuentaRepository;

public class GetAllCategoriaCuentaHandler implements RequestHandler<GetAllCategoriaCuentaQuery, List<CategoriaCuentaDto>> {

    public ICategoriaCuentaRepository _categoriaCuentaRepository;

    public GetAllCategoriaCuentaHandler(ICategoriaCuentaRepository categoriaCuentaRepository) {
        this._categoriaCuentaRepository = categoriaCuentaRepository;
    }

    @Override
    public List<CategoriaCuentaDto> handle(GetAllCategoriaCuentaQuery request) throws Exception {
        List<CategoriaCuentaDto> resp = new ArrayList<>();
        for (CategoriaCuenta categoriaCuenta : _categoriaCuentaRepository.GetAll()) {
            resp.add(new CategoriaCuentaDto(categoriaCuenta));
        } 
        return resp;
    }

}
