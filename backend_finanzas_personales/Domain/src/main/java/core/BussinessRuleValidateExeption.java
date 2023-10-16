package core;

import java.io.Serializable;

public class BussinessRuleValidateExeption extends Exception implements Serializable {

  public BussinessRule BrokenRule;
  public String Details;

  public BussinessRuleValidateExeption(String detail) {
    Details = detail;
  }

  public BussinessRuleValidateExeption(BussinessRule brokenRule) {
    BrokenRule = brokenRule;
    Details = brokenRule.Message();
  }

  public String getDetails() {
    return Details;
  }

  // private void setDetails(String details) {
  // Details = details;
  // }

  public BussinessRule getBrokenRule() {
    return BrokenRule;
  }

  // private void setBrokenRule(BussinessRule brokenRule) {
  // BrokenRule = brokenRule;
  // }

  @Override
  public String toString() {
    String name = BrokenRule == null ? "BussinessRule" : BrokenRule.getClass().getName();
    return name + ':' + Details;
  }
}
