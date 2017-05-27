package ua.km.khnu.virtual.university.web.teacher.subject.instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;
import ua.km.khnu.virtual.university.refrence.Semester;
import ua.km.khnu.virtual.university.repositories.TeacherRepository;
import ua.km.khnu.virtual.university.repositories.TeacherSubjectInstanceRepository;
import ua.km.khnu.virtual.university.service.SubjectInstanceService;

import java.util.List;

/**
 * @author Igor Rybak
 */
@Controller
public class TeacherSubjectInstanceController {
    private final TeacherRepository teacherRepository;
    private final TeacherSubjectInstanceRepository teacherSubjectInstanceRepository;
    private final SubjectInstanceService subjectInstanceService;

    @Autowired
    public TeacherSubjectInstanceController(TeacherSubjectInstanceRepository teacherSubjectInstanceRepository, TeacherRepository teacherRepository, SubjectInstanceService subjectInstanceService) {
        this.teacherSubjectInstanceRepository = teacherSubjectInstanceRepository;
        this.teacherRepository = teacherRepository;
        this.subjectInstanceService = subjectInstanceService;
    }


    @ModelAttribute("teacherSubjectInstance")
    public TeacherSubjectInstance teacherSubjectInstance() {
        return new TeacherSubjectInstance();
    }

    private void initModel(Model model, @PathVariable int subjectInstanceId) {
        List<TeacherSubjectInstance> teacherSubjectInstances = teacherSubjectInstanceRepository.findBySubjectInstanceId(subjectInstanceId);
        model.addAttribute("teacherSubjectInstances", teacherSubjectInstances);
        model.addAttribute("teachers", teacherRepository.findAll());
        SubjectInstance subjectInstance= subjectInstanceService.get(subjectInstanceId);
        model.addAttribute(subjectInstance);
    }

    @GetMapping("/subject-instances/{subjectInstanceId}/teacher-subject-instances")
    public String getTeacherSubjectInstances(
            Model model,
            @PathVariable int subjectInstanceId
    ) {
        initModel(model, subjectInstanceId);
        return "teacher-subject-instance/teacher-subject-instances";
    }

    @PostMapping("/subject-instances/{subjectInstanceId}/teacher-subject-instances")
    public String postTeacherSubjectInstance(
            @ModelAttribute("teacherSubjectInstance") TeacherSubjectInstance teacherSubjectInstance,
            Model model,
            BindingResult result,
            @PathVariable int subjectInstanceId
    ) {
        if (result.hasErrors()) {
            initModel(model, subjectInstanceId);
            return "teacher-subject-instances";
        }
        teacherSubjectInstance.setSubjectInstance(new SubjectInstance(subjectInstanceId));
        teacherSubjectInstanceRepository.save(teacherSubjectInstance);
        return "redirect:/subject-instances/" + subjectInstanceId + "/teacher-subject-instances";
    }

    @GetMapping("/subject-instances/{subjectInstanceId}/delete-teacher-subject-instance/{teacherSubjectInstanceId}")
    public String deleteTeacherSubjectInstance(
            @PathVariable int teacherSubjectInstanceId
    ) {
        teacherSubjectInstanceRepository.delete(teacherSubjectInstanceId);
        return "redirect:/subject-instances/{subjectInstanceId}/teacher-subject-instances";
    }
}
