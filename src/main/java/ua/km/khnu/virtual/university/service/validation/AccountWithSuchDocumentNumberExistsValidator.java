package ua.km.khnu.virtual.university.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ua.km.khnu.virtual.university.repositories.AccountRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Igor Rybak
 */
public class AccountWithSuchDocumentNumberExistsValidator implements ConstraintValidator<AccountWithSuchDocumentNumberExists, String> {
    @Autowired
    private AccountRepository accountRepository;

    public void initialize(AccountWithSuchDocumentNumberExists constraint) {}

    public boolean isValid(String documentNumber, ConstraintValidatorContext context) {
        return accountRepository.existsByDocumentNumber(documentNumber);
    }
}
