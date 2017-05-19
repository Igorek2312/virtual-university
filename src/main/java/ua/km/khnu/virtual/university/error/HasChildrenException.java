package ua.km.khnu.virtual.university.error;

/**
 * @author Igor Rybak
 */
public class HasChildrenException extends CustomException {

    @Override
    public int getCode() {
        return 2;
    }

}
