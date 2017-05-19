package ua.km.khnu.virtual.university.web.specialty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Faculty;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.repositories.FacultyRepository;
import ua.km.khnu.virtual.university.repositories.SpecialtyRepository;

import java.util.List;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class SpecialtyController {
    private final SpecialtyRepository specialtyRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public SpecialtyController(SpecialtyRepository specialtyRepository, FacultyRepository facultyRepository) {
        this.specialtyRepository = specialtyRepository;
        this.facultyRepository = facultyRepository;
    }

    @ModelAttribute("specialty")
    public Specialty specialty(@PathVariable int facultyId) {
        Specialty specialty = new Specialty();
        Faculty faculty = retrieveOneOrThrowNotFound(facultyRepository::findOne, facultyId, Faculty.class);
        specialty.setFaculty(faculty);
        return specialty;
    }

    @ModelAttribute("faculty")
    public Faculty faculty(@PathVariable int facultyId) {
        return retrieveOneOrThrowNotFound(facultyRepository::findOne, facultyId, Faculty.class);
    }

    @ModelAttribute("specialties")
    public List<Specialty> specialties(@PathVariable int facultyId) {
        return specialtyRepository.findByFacultyId(facultyId);
    }

    @GetMapping("/faculties/{facultyId}/specialties")
    public String getSpecialtiesByFaculty() {
        return "specialty/specialties";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/faculties/{facultyId}/specialties")
    public String postSpecialty(
            @ModelAttribute("specialty") @Validated Specialty specialty,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "specialty/specialties";
        }
        specialtyRepository.save(specialty);
        return "redirect:/faculties/" + specialty.getFaculty().getId() + "/specialties";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/faculties/{facultyId}/delete-specialty/{specialtyId}")
    public String deleteSpecial(
            @PathVariable int facultyId,
            @PathVariable int specialtyId
    ) {
        specialtyRepository.delete(specialtyId);
        return "redirect:/faculties/" + facultyId + "/specialties";
    }
}
