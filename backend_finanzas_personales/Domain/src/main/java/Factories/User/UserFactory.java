package Factories.User;

import Model.User;
import core.BussinessRuleValidateExeption;

public class UserFactory implements IUserFactory {

    @Override
    public User Create(String email, String password) throws BussinessRuleValidateExeption {
        return new User(email, password) ;
    }
    
}
