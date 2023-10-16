package UseCases.Queries.User.FindByKey;

import java.util.UUID;

import Fourteam.mediator.Request;

public class FindUserByKeyQuery implements Request<UUID> {
    public UUID key;
    public String token;
    
    public FindUserByKeyQuery(UUID key){
        this.key = key;
    }
}
