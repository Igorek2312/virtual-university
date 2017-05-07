package ua.km.khnu.virtual.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.error.NoAccountWithSuchDocumentNumber;
import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.repositories.AccountRepository;
import ua.km.khnu.virtual.university.transfare.EnableAccountForm;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getByDocumentNumber(String documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(NoAccountWithSuchDocumentNumber::new);
    }

    @Override
    public Account enableAccount(int accountId, EnableAccountForm form) {
        Account account = retrieveOneOrThrowNotFound(accountRepository::findOne, accountId, Account.class);
        if (account.getDocumentNumber().equals(form.getDocumentNumber())) {
            account.setEnabled(form.isEnabled());
        } else {
            throw new NoAccountWithSuchDocumentNumber();
        }

        return account;
    }
}
