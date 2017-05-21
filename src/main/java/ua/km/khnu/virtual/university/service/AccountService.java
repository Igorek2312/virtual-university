package ua.km.khnu.virtual.university.service;

import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.transfare.legacy.EnableAccountForm;

/**
 * @author Igor Rybak
 */
public interface AccountService {
    Account getByDocumentNumber(String documentNumber);

    Account enableAccount(int studentId, EnableAccountForm form);
}
