package Rule;

import core.BussinessRule;

public class StringNotNullOrEmptyRule implements BussinessRule {

  private String _value;

  public StringNotNullOrEmptyRule(String value) {
    _value = value;
  }

 
  public boolean IsValid() {
    if (_value != null && !_value.isEmpty()) {
      return true;
    }
    return false;
  }


  public String Message() {
    return "string cannot be null";
  }
}
