package UseCases.Command.Movimiento.Eliminar;

import java.util.UUID;

import Fourteam.mediator.RequestHandler;

public class EliminarMovimientoHandler implements RequestHandler<EliminarMovimientoCommand, UUID>{

    @Override
    public UUID handle(EliminarMovimientoCommand request) throws Exception {
        // TODO Falta Implementar.
        return new UUID(0,0);
    }
    
}
