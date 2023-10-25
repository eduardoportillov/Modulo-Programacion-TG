package core;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity<TId> {

  public TId key;
  public transient List<DomainEvent> domainEvents;

  // private Collection<DomainEvent> DomainEvents;

  public List<DomainEvent> getDomainEvents() {
    return domainEvents;
  }

  public Entity() {
    domainEvents = new ArrayList<DomainEvent>();
  }

  public void addDomainEvent(DomainEvent event) {
    if(domainEvents == null) {
      domainEvents = new ArrayList<DomainEvent>();
    }
    domainEvents.add(event);
  }

  public void clearDomainEvent() {
    domainEvents.clear();
  }

  public TId getKey() {
    return key;
  }

  protected void setKey(TId key) {
    this.key = key;
  }

  protected void CheckRule(BussinessRule rule) throws BussinessRuleValidateExeption {
    if (rule == null) {
      throw new IllegalArgumentException("Rule cannot be null");
    }
    if (!rule.IsValid()) {
      throw new BussinessRuleValidateExeption(rule);
    }
  }
}
