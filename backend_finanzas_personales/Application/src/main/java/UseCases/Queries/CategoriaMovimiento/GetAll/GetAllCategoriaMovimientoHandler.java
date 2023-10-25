package UseCases.Queries.CategoriaMovimiento.GetAll;

import java.util.ArrayList;
import java.util.List;

import Dto.CategoriaMovimientoDto;
import Fourteam.mediator.RequestHandler;
import Model.CategoriaMovimiento;
import Repositories.ICategoriaMovimientoRepository;

public class GetAllCategoriaMovimientoHandler implements RequestHandler<GetAllCategoriaMovimientoQuery, List<CategoriaMovimientoDto>> {

    public ICategoriaMovimientoRepository _categoriaMovimientoRepository;

    public GetAllCategoriaMovimientoHandler(ICategoriaMovimientoRepository categoriaMovimientoRepository) {
        this._categoriaMovimientoRepository = categoriaMovimientoRepository;
    }

    @Override
    public List<CategoriaMovimientoDto> handle(GetAllCategoriaMovimientoQuery request) throws Exception {
        List<CategoriaMovimientoDto> resp = new ArrayList<>();
        for (CategoriaMovimiento categoriaMovimiento : _categoriaMovimientoRepository.GetAll()) {
            resp.add(new CategoriaMovimientoDto(categoriaMovimiento));
        } 
        return resp;
    }

}
