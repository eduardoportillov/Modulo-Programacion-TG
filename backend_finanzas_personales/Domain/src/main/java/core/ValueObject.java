package core;

public abstract class ValueObject {

  protected void CheckRule(BussinessRule rule) throws BussinessRuleValidateExeption {
    if (rule == null) {
      throw new IllegalArgumentException("Rule cannot be null");
    }
    if (!rule.IsValid()) {
      throw new BussinessRuleValidateExeption(rule);
    }
  }
}
