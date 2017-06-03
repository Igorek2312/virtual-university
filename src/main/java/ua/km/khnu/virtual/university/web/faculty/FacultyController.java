package ua.km.khnu.virtual.university.web.faculty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Faculty;
import ua.km.khnu.virtual.university.repositories.FacultyRepository;

import java.util.List;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;


/**
 * @author Igor Rybak
 */
@Controller
public class FacultyController {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @ModelAttribute("faculty")
    public Faculty faculty() {
        return new Faculty();
    }

    @ModelAttribute("editFaculty")
    public Faculty editFaculty(@PathVariable(required = false) Integer facultyId) {
        if (facultyId == null) return null;
        return retrieveOneOrThrowNotFound(facultyRepository::findOne, facultyId, Faculty.class);
    }

    private void initModel(Model model) {
        List<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("faculties",faculties);
    }

    @GetMapping("/faculties")
    public String getFaculties(
            Model model
    ) {
        initModel(model);
        return "faculty/faculties";
    }

    @PostMapping("/faculties")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String postFaculty(
            @ModelAttribute("faculty") @Validated Faculty faculty,
            BindingResult result,
            Model model
    ) {
        if (result.hasErrors()) {
            initModel(model);
            return "faculty/faculties";
        }
        facultyRepository.save(faculty);
        return "redirect:/faculties";
    }

    @GetMapping("/edit-faculty/{facultyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditFaculty() {
        return "faculty/edit-faculty";
    }

    @PostMapping("/edit-faculty/{facultyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateFaculty(
            @ModelAttribute("editFaculty") @Validated Faculty faculty,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "faculty/edit-faculty";
        }
        facultyRepository.save(faculty);
        return "redirect:/faculties";
    }

    @GetMapping("/delete-faculty/{facultyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteFaculty(
            @PathVariable int facultyId
    ) {
        facultyRepository.delete(facultyId);
        return "redirect:/faculties";
    }
}
