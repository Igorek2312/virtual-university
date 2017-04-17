package ua.km.khnu.virtual.university.error;

import org.springframework.http.HttpStatus;

/**
 * Thrown according to 401 HTTP status code in RESTful web services.
 *
 * @author Igor Rybak
 */
public class AccessDeniedCustomException extends CustomException {
    @Override
    public int getCode() {
        return 401;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public String getDescription() {
        return HttpStatus.UNAUTHORIZED.getReasonPhrase();
    }

}
