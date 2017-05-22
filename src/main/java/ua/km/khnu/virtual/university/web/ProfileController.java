package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.km.khnu.virtual.university.model.Role;
import ua.km.khnu.virtual.university.repositories.AccountRepository;
import ua.km.khnu.virtual.university.repositories.StudentRepository;
import ua.km.khnu.virtual.university.util.SecurityUtils;

/**
 * @author Igor Rybak
 */
@Controller
public class ProfileController {
    private final AccountRepository accountRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public ProfileController(AccountRepository accountRepository, StudentRepository studentRepository) {
        this.accountRepository = accountRepository;
        this.studentRepository = studentRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String getProfile(Model model) {
        String login = SecurityUtils.getCurrentUserLogin();

        accountRepository.findByUsername(login).ifPresent(account -> {
            model.addAttribute(account);
            account.getRoles().stream()
                    .map(Role::getAuthority)
                    .filter(role -> role.equals("ROLE_STUDENT"))
                    .findFirst()
                    .ifPresent(
                            s -> studentRepository.findByAccountId(account.getId()).ifPresent(model::addAttribute)
                    );
        });

        return "profile";
    }
}
