import Context.IWriteDbContext;
import Fourteam.db.Exception.DataBaseException;
import Fourteam.mediator.Mediator;
import Repositories.IUnitOfWork;
import core.ConfirmedDomainEvent;
import core.DomainEvent;
import java.util.List;

public class UnitOfWork implements IUnitOfWork {

  private IWriteDbContext _context;
  private Mediator _mediator;

  public UnitOfWork(IWriteDbContext context, Mediator mediator) {
    _context = context;
    _mediator = mediator;
  }

  @Override
  public void commit() throws Exception {
    List<Object> events = _context.getDomainEvents();
    for (Object domainEvent : events) {
      try {
        DomainEvent event = (DomainEvent) domainEvent;
        _mediator.notify(event);
      } catch (Exception e) {}
    }
    try {
      _context.Commit();
    } catch (DataBaseException e) {}

    for (Object domainEvent : events) {
      try {
        // (domainEvent.getClass(), domainEvent)
        _mediator.notify(MakeGeneryc(domainEvent.getClass(), domainEvent));
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }

  public <T> ConfirmedDomainEvent<T> MakeGeneryc(Class<?> c, T o) {
    return new ConfirmedDomainEvent<T>(o);
  }
}
