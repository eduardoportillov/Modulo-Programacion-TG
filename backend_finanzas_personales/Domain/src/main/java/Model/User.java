package Model;

import java.util.UUID;
import DomainEvents.User.UserCreado;
import Rule.EmailRule;
import Rule.StringNotNullOrEmptyRule;
import core.AggregateRoot;
import core.BussinessRuleValidateExeption;

public class User extends AggregateRoot<UUID> {
    public String email;
    public String password;

    public User(String email, String password) throws BussinessRuleValidateExeption {
        CheckRule(new EmailRule(email)); // TODO no me tira el mensaje de la ruta en la peticion post, me sale Error desconocido, null
        CheckRule(new StringNotNullOrEmptyRule(email));
        CheckRule(new StringNotNullOrEmptyRule(password));

        key = UUID.randomUUID();
        this.email = email;

        this.password = password;
    }

    public void eventCreado() {
        addDomainEvent(new UserCreado(this.key));
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UUID getKey() {
        return key;
    }
}
