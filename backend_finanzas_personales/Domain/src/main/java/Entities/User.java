package Entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.google.gson.Gson;

import DomainEvents.UserCreado;
import Entities.Cuenta.CategoriaCuenta;
import Entities.Movimiento.CategoriaMovimiento;
import Rule.EmailRule;
import Rule.StringNotNullOrEmptyRule;
import core.AggregateRoot;
import core.BussinessRuleValidateExeption;

public class User extends AggregateRoot<UUID> {
    public String email;
    public String password;
    public List<CategoriaCuenta> categoriaCuentaUser;
    public List<CategoriaMovimiento> categoriaMovimientoUser;

    public User(String email, String password) throws BussinessRuleValidateExeption {
        CheckRule(new EmailRule(email)); // TODO no me tira el mensaje de la ruta en la peticion post, me sale Error
                                         // desconocido, null
        CheckRule(new StringNotNullOrEmptyRule(email));
        CheckRule(new StringNotNullOrEmptyRule(password));

        key = UUID.randomUUID();
        this.categoriaCuentaUser = new ArrayList<>();
        this.categoriaMovimientoUser = new ArrayList<>();
        this.email = email;

        this.password = password;
    }

    public void eventCreado(UUID keyUser) {
        addDomainEvent(new UserCreado(keyUser));
    }

    public void addCategoriaCuentaUser(CategoriaCuenta categoriaCuentaUser) {
        this.categoriaCuentaUser.add(categoriaCuentaUser);
    }

    public void addCategoriaMovimientoUser(CategoriaMovimiento categoriaMovimientoUser) {
        this.categoriaMovimientoUser.add(categoriaMovimientoUser);
    }

    public boolean isCategoriaMovimientoUser(UUID keyCategoria) {
        return this.categoriaMovimientoUser.stream().noneMatch(categoria -> categoria.getKey().equals(keyCategoria));
    }

    public boolean isCategoriaCuentaUser(UUID keyCategoria) {
        return this.categoriaCuentaUser.stream().noneMatch(categoria -> categoria.getKey().equals(keyCategoria));
    }

    public String generateTokenWithUser() {
        int expirationTime = 9000 * 60 * 10;
        String jsonUser = new Gson().toJson(this);
        String token = JWT.JWT.create(jsonUser, expirationTime);
        return token;
    }

    public static User decodeTokenWithUser(String token) {
        String jsonUser = JWT.JWT.decode(token);
        User user = new Gson().fromJson(jsonUser, User.class);
        return user;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<CategoriaCuenta> getCategoriaCuentaUser() {
        return categoriaCuentaUser;
    }

    public List<CategoriaMovimiento> getCategoriaMovimientoUser() {
        return categoriaMovimientoUser;
    }

    public UUID getKey() {
        return key;
    }
}
