package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.km.khnu.virtual.university.error.CustomException;
import ua.km.khnu.virtual.university.error.ErrorInfo;

/**
 * Controller contains methods to handle common exceptions
 *
 * @author Igor Rybak
 */
@RestControllerAdvice
public class ExceptionHandlerController {
    @Autowired
    private MessageSource messageSource;

    /**
     * Handles {@link CustomException}. Retrieve message from message source
     * if {@link CustomException#getMessageKey() message key} is present.
     *
     * @param e CustomException instance
     * @return response with body of information about error and corresponding status
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorInfo> handleBoilerplateException(CustomException e) {
        ErrorInfo errorInfo = new ErrorInfo(e.getCode(), e.getInterpolatedDescription());

        e.getMessageKey().ifPresent(messageKey -> {
            String message = messageSource.getMessage(
                    messageKey,
                    e.getMessageArgs(),
                    LocaleContextHolder.getLocale()
            );
            errorInfo.setMessage(message);
        });

        return new ResponseEntity<>(errorInfo, e.getHttpStatus());
    }
}