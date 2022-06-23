package textify.api.models.exceptions;

import org.springframework.data.rest.core.event.ExceptionEvent;

public class RequestError extends ExceptionEvent {

  public RequestError(Throwable t) {
    super(t);
  }
}
