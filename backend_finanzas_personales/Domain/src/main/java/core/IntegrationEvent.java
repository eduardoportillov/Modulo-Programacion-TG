package core;

import Fourteam.mediator.Notification;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class IntegrationEvent implements Notification {

  public LocalDateTime OcurredOn;
  public UUID Key;

  public IntegrationEvent() {
  }

  public IntegrationEvent(LocalDateTime ocurredOn) {
    this.OcurredOn = ocurredOn;
    this.Key = UUID.randomUUID();
  }

  public LocalDateTime getOcurredOn() {
    return OcurredOn;
  }

  public UUID getKey() {
    return Key;
  }

  @Override
  public String getGenericName() {
    return null;
  }
}
