package Factories.User;

import Model.User;
import core.BussinessRuleValidateExeption;

public interface IUserFactory {
    public User Create(String email, String password) throws BussinessRuleValidateExeption ;
}
