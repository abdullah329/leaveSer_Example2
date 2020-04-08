package sa.gov.sfd.leave.web.infrastructure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class MyForbiddenException extends RuntimeException {

    public MyForbiddenException() {
        super();
    }

    public MyForbiddenException(final String message) {
        super(message);
    }

    public MyForbiddenException(final Throwable cause) {
        super(cause);
    }

}
