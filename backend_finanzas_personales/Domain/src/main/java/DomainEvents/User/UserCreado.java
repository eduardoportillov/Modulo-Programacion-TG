package DomainEvents.User;

import java.util.UUID;

import core.DomainEvent;

public class UserCreado extends DomainEvent {

    private UUID keyUser;

    public UserCreado(UUID keyUser) {
        this.keyUser = keyUser;
    }

    public UUID getKeyUser() {
        return keyUser;
    }
    
}
