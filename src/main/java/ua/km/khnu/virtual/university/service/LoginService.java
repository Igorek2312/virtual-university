package ua.km.khnu.virtual.university.service;

import ua.km.khnu.virtual.university.transfare.LoginForm;

import java.io.IOException;

/**
 * @author Igor Rybak
 */
public interface LoginService {
    String login(LoginForm form) throws IOException;
}
