package ua.km.khnu.virtual.university.error.legacy;

import org.springframework.http.HttpStatus;

import java.util.Optional;

/**
 * {@code CustomException} is supper class for exceptions to be thrown and handled in
 * {@code @ControllerAdvice}. Contains all required information for analyzing error from server response.
 *
 * @author Igor Rybak
 */
public abstract class CustomException extends RuntimeException {
    private Object[] descriptionArgs;
    private Object[] messageArgs = null;
    private String description;
    private String messageKey;

    public CustomException() {
    }

    public CustomException(String description) {
        this.description = description;
    }

    public CustomException(String description, String messageKey) {
        this.description = description;
        this.messageKey = messageKey;
    }

    /**
     * @return formatted description with argument
     * @see CustomException#setDescriptionArgs
     */
    public final String getInterpolatedDescription() {
        return String.format(getDescription(), descriptionArgs);
    }

    /**
     * @param descriptionArgs arguments for description formatting
     */
    public final void setDescriptionArgs(Object... descriptionArgs) {
        this.descriptionArgs = descriptionArgs;
    }

    /**
     * @return arguments for user localized message
     */
    public Object[] getMessageArgs() {
        return messageArgs;
    }

    /**
     * @param messageArgs arguments for user localized message
     */
    public final void setMessageArgs(Object... messageArgs) {
        this.messageArgs = messageArgs;
    }

    /**
     * @return unique error code
     */
    public abstract int getCode();

    /**
     * @return message key to retrieve from {@code MessageSource}
     */
    public Optional<String> getMessageKey() {
        return Optional.ofNullable(messageKey);
    }

    /**
     * @return description of exception
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return appropriate HTTP status code
     */
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
