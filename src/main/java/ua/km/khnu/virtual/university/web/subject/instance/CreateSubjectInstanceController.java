package ua.km.khnu.virtual.university.web.subject.instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.repositories.SubjectRepository;
import ua.km.khnu.virtual.university.service.SubjectInstanceService;
import ua.km.khnu.virtual.university.transfare.SemesterDto;

import java.util.List;

/**
 * @author Igor Rybak
 */
@Controller
public class CreateSubjectInstanceController {
    private final SubjectRepository subjectRepository;
    private final SubjectInstanceService subjectInstanceService;

    @Autowired
    public CreateSubjectInstanceController(SubjectInstanceService subjectInstanceService, SubjectRepository subjectRepository) {
        this.subjectInstanceService = subjectInstanceService;
        this.subjectRepository = subjectRepository;
    }

    @ModelAttribute("groupId")
    public int groupId(@PathVariable int groupId) {
        return groupId;
    }

    @ModelAttribute("subjects")
    public List<Subject> subjects() {
        return subjectRepository.findAll();
    }

    @ModelAttribute("subjectInstance")
    public SubjectInstance subjectInstance() {
        return subjectInstanceService.prepareNew();
    }

    @ModelAttribute("semesters")
    public List<SemesterDto> semesters(@PathVariable int groupId) {
        return subjectInstanceService.getSemesters(groupId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/groups/{groupId}/create-subject-instance")
    public String getSubjectInstanceForm() {
        return "subject/instance/create-subject-instance";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groups/{groupId}/subject-instances")
    public String postSubjectInstance(
            @ModelAttribute("subjectInstance") @Validated SubjectInstance subjectInstance,
            BindingResult result,
            @PathVariable int groupId
    ) {
        if (result.hasErrors()) {
            return "subject-instance";
        }
        int subjectId = subjectInstance.getSubject().getId();
        subjectInstanceService.create(subjectInstance, groupId, subjectId);
        return "redirect:/groups/" + groupId + "/create-subject-instance";
    }
}
