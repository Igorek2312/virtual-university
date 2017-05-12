package ua.km.khnu.virtual.university.error;

import org.springframework.http.HttpStatus;

/**
 * @author Igor Rybak
 */
public class ConflictException extends CustomException {
    public ConflictException(String description) {
        super(description);
    }

    @Override
    public int getCode() {
        return 2;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
