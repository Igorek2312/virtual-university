package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Account;
import ua.km.khnu.virtual.university.service.AccountService;
import ua.km.khnu.virtual.university.transfare.EnableAccountForm;

/**
 * @author Igor Rybak
 */
@RestController
public class AccountController {
    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public Account getByDocumentNumber(@RequestParam("document-number") String documentNumber) {
        return accountService.getByDocumentNumber(documentNumber);
    }

    @PatchMapping("/accounts/{accountId}/enabled")
    public Account enableStudent(@PathVariable int accountId, EnableAccountForm form) {
        return accountService.enableAccount(accountId, form);
    }
}
