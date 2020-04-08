package sa.gov.sfd.leave.web.infrastructure.exceptions;

public final class MyBadRequestException extends RuntimeException {

    public MyBadRequestException() {
        super();
    }

    public MyBadRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public MyBadRequestException(final String message) {
        super(message);
    }

    public MyBadRequestException(final Throwable cause) {
        super(cause);
    }

}
