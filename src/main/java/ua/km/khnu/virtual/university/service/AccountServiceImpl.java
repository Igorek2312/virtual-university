package ua.km.khnu.virtual.university.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.error.legacy.NoAccountWithSuchDocumentNumberException;
import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.repositories.AccountRepository;
import ua.km.khnu.virtual.university.transfare.EnableAccountForm;

import static ua.km.khnu.virtual.university.util.legacy.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Account getByDocumentNumber(String documentNumber) {
        return accountRepository.findByDocumentNumber(documentNumber)
                .orElseThrow(NoAccountWithSuchDocumentNumberException::new);
    }

    @Override
    public Account enableAccount(int accountId, EnableAccountForm form) {
        Account account = retrieveOneOrThrowNotFound(accountRepository::findOne, accountId, Account.class);
        if (account.getDocumentNumber().equals(form.getDocumentNumber())) {
            modelMapper.map(form, account);
            accountRepository.save(account);
        } else {
            throw new NoAccountWithSuchDocumentNumberException();
        }

        return account;
    }
}
