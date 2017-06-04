package ua.km.khnu.virtual.university.web.subject.instance;

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
import ua.km.khnu.virtual.university.model.Group;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.refrence.Semester;
import ua.km.khnu.virtual.university.repositories.GroupRepository;
import ua.km.khnu.virtual.university.repositories.SubjectRepository;
import ua.km.khnu.virtual.university.service.SubjectInstanceService;

import java.util.List;

/**
 * @author Igor Rybak
 */
@Controller
public class CreateSubjectInstanceController {
    private final SubjectRepository subjectRepository;
    private final SubjectInstanceService subjectInstanceService;
    private final GroupRepository groupRepository;

    @Autowired
    public CreateSubjectInstanceController(SubjectInstanceService subjectInstanceService, SubjectRepository subjectRepository, GroupRepository groupRepository) {
        this.subjectInstanceService = subjectInstanceService;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
    }

    @ModelAttribute("groupId")
    public int groupId(@PathVariable int groupId) {
        return groupId;
    }

    @ModelAttribute("subjectInstance")
    public SubjectInstance subjectInstance() {
        return subjectInstanceService.prepareNew();
    }

    private void initModel(Model model, int groupId) {
        List<Semester> semesters = subjectInstanceService.getSemesters(groupId);
        model.addAttribute("semesters", semesters);
        List<Subject> subjects = subjectRepository.findAll();
        model.addAttribute("subjects", subjects);
        Group group = groupRepository.findOne(groupId);
        model.addAttribute("group", group);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/groups/{groupId}/create-subject-instance")
    public String getSubjectInstanceForm(
            Model model,
            @PathVariable int groupId
    ) {
        initModel(model, groupId);
        return "subject/instance/create-subject-instance";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/groups/{groupId}/subject-instances")
    public String postSubjectInstance(
            @ModelAttribute("subjectInstance") @Validated SubjectInstance subjectInstance,
            BindingResult result,
            Model model,
            @PathVariable int groupId
    ) {
        if (result.hasErrors()) {
            initModel(model, groupId);
            return "subject/instance/create-subject-instance";
        }
        int subjectId = subjectInstance.getSubject().getId();
        subjectInstanceService.create(subjectInstance, groupId, subjectId);
        return "redirect:/groups/{groupId}/create-subject-instance";
    }
}
