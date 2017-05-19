package ua.km.khnu.virtual.university.error.legacy;

/**
 * A transfer object for error representation.
 *
 * @author Igor Rybak
 */
public class ErrorInfo {
    private final int errorCode;

    private String message;

    private final String description;


    public ErrorInfo(int errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
