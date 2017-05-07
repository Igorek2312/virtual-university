package ua.km.khnu.virtual.university.error;

import org.springframework.http.HttpStatus;

/**
 * Thrown according to 403 HTTP status code in RESTful web services.
 *
 * @author Igor Rybak
 */
public class ForbiddenCustomException extends CustomException {
    @Override
    public int getCode() {
        return 403;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.FORBIDDEN;
    }

    @Override
    public String getDescription() {
        return HttpStatus.FORBIDDEN.getReasonPhrase();
    }
}
