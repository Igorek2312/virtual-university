package ua.km.khnu.virtual.university.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.km.khnu.virtual.university.service.LoginService;
import ua.km.khnu.virtual.university.transfare.LoginForm;

import java.io.IOException;

@RestController
public class LoginController {
    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @ApiOperation(
            value = "remove Bearer in Authorization; Usernames/password: teacher/4321 - teacher; student/1234 - student;"
    )
    @GetMapping("/login")
    public Object login(LoginForm form) throws IOException {
        return loginService.login(form);
    }


}
