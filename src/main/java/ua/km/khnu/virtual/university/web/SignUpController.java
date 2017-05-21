package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.repositories.AccountRepository;
import ua.km.khnu.virtual.university.transfare.SignUpForm;

/**
 * @author Igor Rybak
 */
@Controller
public class SignUpController {
    private final AccountRepository accountRepository;

    @Autowired
    public SignUpController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @ModelAttribute("signUpForm")
    public SignUpForm signUpForm() {
        return new SignUpForm();
    }

    @GetMapping("/sign-up")
    public String getSignUp() {
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
            @ModelAttribute("signUpForm") @Validated SignUpForm form,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "sign-up";
        }

        accountRepository.findByDocumentNumber(form.getDocumentNumber())
                .ifPresent(account -> {
                    account.setUsername(form.getUsername());
                    account.setPassword(form.getPassword());
                    account.setEnabled(true);
                    accountRepository.save(account);
                });

        return "redirect:/login?signed-up";
    }
}
