package ua.km.khnu.virtual.university.transfare;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import ua.km.khnu.virtual.university.service.validation.AccountWithSuchDocumentNumberExists;
import ua.km.khnu.virtual.university.service.validation.AccountWithSuchDocumentNumberIsDisabled;

/**
 * @author Igor Rybak
 */
public class SignUpForm {
    @NotBlank
    @Length(min = 5, max = 20)
    private String username;
    @NotBlank
    @Length(min = 5, max = 20)
    private String password;

    @AccountWithSuchDocumentNumberExists
    @AccountWithSuchDocumentNumberIsDisabled
    @NotBlank
    private String documentNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}
