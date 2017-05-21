package ua.km.khnu.virtual.university.web.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.repositories.SubjectRepository;

/**
 * @author Igor Rybak
 */
@Controller
public class EditSubjectController {
    private final SubjectRepository subjectRepository;

    @Autowired
    public EditSubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @ModelAttribute("subject")
    public Subject subject(@PathVariable int subjectId) {
        return subjectRepository.findOne(subjectId);
    }

    @GetMapping("/edit-subject/{subjectId}")
    public String getSubjectToEdit(
            @ModelAttribute("subject") Subject subject
    ) {
        return "subject/edit-subject";
    }

    @PostMapping("/edit-subject/{subjectId}")
    public String postSubject(
            @ModelAttribute("subject") @Validated Subject subject,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "subject/edit-subject";
        }
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }
}
