package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.km.khnu.virtual.university.service.FacultyService;

/**
 * @author Igor Rybak
 */
@Controller
@RequestMapping("/faculties")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public String faculties(Model model){
        return "faculties";
    }
}
