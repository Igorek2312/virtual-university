package ua.km.khnu.virtual.university.web.subject.instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.repositories.SubjectInstanceRepository;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class EditSubjectInstanceController {
    private final SubjectInstanceRepository subjectInstanceRepository;

    @Autowired
    public EditSubjectInstanceController(SubjectInstanceRepository subjectInstanceRepository) {
        this.subjectInstanceRepository = subjectInstanceRepository;
    }

    @ModelAttribute("subjectInstance")
    public SubjectInstance subjectInstance(@PathVariable int subjectInstanceId) {
        return retrieveOneOrThrowNotFound(subjectInstanceRepository::findOne, subjectInstanceId, SubjectInstance.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/edit-subject-instance/{subjectInstanceId}")
    public String getSubjectInstance() {
        return "subject/instance/edit-subject-instance";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/edit-subject-instance/{subjectInstanceId}")
    public String updateSubjectInstance(
            @ModelAttribute("subjectInstance") SubjectInstance subjectInstance,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "subject/instance/edit-subject-instance";
        }
        subjectInstanceRepository.save(subjectInstance);
        Integer groupId = subjectInstance.getGroup().getId();
        return "redirect:/groups/" + groupId + "/create-subject-instance";
    }
}
