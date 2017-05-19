package ua.km.khnu.virtual.university.error.legacy;

/**
 * @author Igor Rybak
 */
public class WrongDocumentNumberException extends CustomException {
    @Override
    public int getCode() {
        return 2;
    }

    @Override
    public String getDescription() {
        return "Wrong document number exception.";
    }
}
