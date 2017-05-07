package ua.km.khnu.virtual.university.transfare;

/**
 * @author Igor Rybak
 */
public class EnableAccountForm {
    private String documentNumber;

    private boolean enabled;

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
