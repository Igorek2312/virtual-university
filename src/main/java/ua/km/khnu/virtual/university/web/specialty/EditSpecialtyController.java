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
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.repositories.SpecialtyRepository;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class EditSpecialtyController {
    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public EditSpecialtyController(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @ModelAttribute("specialty")
    public Specialty specialty(@PathVariable int specialtyId) {
        return retrieveOneOrThrowNotFound(specialtyRepository::findOne, specialtyId, Specialty.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit-specialty/{specialtyId}")
    public String getSpecialty() {
        return "specialty/edit-specialty";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit-specialty/{specialtyId}")
    public String updateSpecialty(
            @ModelAttribute("specialty") @Validated Specialty specialty,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "specialty/edit-specialty";
        }
        specialtyRepository.save(specialty);
        return "redirect:/faculties/" + specialty.getFaculty().getId() + "/specialties";
    }
}
