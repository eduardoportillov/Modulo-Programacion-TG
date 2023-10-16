package Rule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.BussinessRule;

public class EmailRule implements BussinessRule{

    String _email;

    public EmailRule(String email) {
        _email = email;
    }

    @Override
    public boolean IsValid() {
        String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = EMAIL_PATTERN.matcher(this._email);
        return matcher.matches();
    }

    @Override
    public String Message() {
        return "el email no es valido";
    }
    
}
