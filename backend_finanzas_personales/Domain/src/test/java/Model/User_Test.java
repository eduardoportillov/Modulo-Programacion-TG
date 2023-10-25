package Model;

import org.junit.Assert;
import org.junit.Test;

import Fourteam.console.console;
import core.BussinessRuleValidateExeption;

public class User_Test {
    @Test
    public void constructor() throws BussinessRuleValidateExeption {
        Assert.assertNotNull(new User("3@gmail.com", "1"));
    }

    @Test
    public void eventoCreado() {
        try {
            User user = new User("3@gmail.com", "1");
            user.eventCreado();
            Assert.assertEquals(user.domainEvents.size(), 1);
        } catch (Exception e) {
            console.error(e.getMessage());
        }
    }
}
