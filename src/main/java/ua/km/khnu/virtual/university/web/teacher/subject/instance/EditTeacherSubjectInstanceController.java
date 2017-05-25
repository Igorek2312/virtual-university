package ua.km.khnu.virtual.university.web.teacher.subject.instance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;
import ua.km.khnu.virtual.university.repositories.TeacherRepository;
import ua.km.khnu.virtual.university.repositories.TeacherSubjectInstanceRepository;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class EditTeacherSubjectInstanceController {
    private final TeacherRepository teacherRepository;
    private final TeacherSubjectInstanceRepository teacherSubjectInstanceRepository;

    @Autowired
    public EditTeacherSubjectInstanceController(TeacherRepository teacherRepository, TeacherSubjectInstanceRepository teacherSubjectInstanceRepository) {
        this.teacherRepository = teacherRepository;
        this.teacherSubjectInstanceRepository = teacherSubjectInstanceRepository;
    }

    @ModelAttribute("teacherSubjectInstanceId")
    public int teacherSubjectInstanceId(@PathVariable int teacherSubjectInstanceId) {
        return teacherSubjectInstanceId;
    }

    @ModelAttribute("teacherSubjectInstance")
    public TeacherSubjectInstance teacherSubjectInstance(
            @PathVariable int teacherSubjectInstanceId
    ) {
        return retrieveOneOrThrowNotFound(
                teacherSubjectInstanceRepository::findOne,
                teacherSubjectInstanceId,
                TeacherSubjectInstance.class
        );
    }

    private void initModel(Model model) {
        model.addAttribute("teachers", teacherRepository.findAll());
    }

    @GetMapping("/edit-teacher-subject-instance/{teacherSubjectInstanceId}")
    public String getTeacherSubjectInstanceToEdit(
            Model model
    ) {
        initModel(model);
        return "teacher-subject-instance/edit-teacher-subject-instance";
    }

    @PostMapping("/edit-teacher-subject-instance/{teacherSubjectInstanceId}")
    public String updateTeacherSubjectInstance(
            @ModelAttribute("teacherSubjectInstance") TeacherSubjectInstance teacherSubjectInstance,
            BindingResult result,
            @PathVariable int teacherSubjectInstanceId
    ) {
        if (result.hasErrors()) {
            return "teacher-subject-instance/edit-teacher-subject-instance";
        }
        teacherSubjectInstanceRepository.save(teacherSubjectInstance);
        return "redirect:/edit-teacher-subject-instance/" + teacherSubjectInstanceId + "?saved";
    }
}
