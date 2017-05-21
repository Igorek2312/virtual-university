package ua.km.khnu.virtual.university.web.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class SubjectController {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @ModelAttribute("subject")
    public Subject Subject() {
        return new Subject();
    }

    @ModelAttribute("subjects")
    public Page<Subject> subjects(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/subjects")
    public String getSubjects() {
        return "subject/subjects";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/subjects")
    public String postSubject(
            @ModelAttribute("subject") @Validated Subject subject,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "subject/subjects";
        }
        subjectRepository.save(subject);
        return "redirect:/subjects";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/delete-subject/{subjectId}")
    public String deleteSubject(
            @PathVariable int subjectId
    ) {
        subjectRepository.delete(subjectId);
        return "redirect:/subjects";
    }
}
