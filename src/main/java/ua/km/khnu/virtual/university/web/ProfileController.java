package ua.km.khnu.virtual.university.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Igor Rybak
 */
@Controller
public class ProfileController {
    @GetMapping("/profile")
    public String getProfile(){
        return "profile";
    }
}
