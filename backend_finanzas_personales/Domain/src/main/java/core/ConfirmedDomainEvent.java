package core;

public class ConfirmedDomainEvent<T> extends DomainEvent{

  public T DomainEvent;

  public ConfirmedDomainEvent(T domainEvent) {
    this.DomainEvent = (T) domainEvent;
  }

  @Override
  public String getGenericName() {
    String dom = super.getGenericName();
    return dom+"<"+this.DomainEvent.getClass().getName()+">";
  }
}
