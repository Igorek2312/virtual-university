package ua.km.khnu.virtual.university.web.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Teacher;
import ua.km.khnu.virtual.university.repositories.TeacherRepository;
import ua.km.khnu.virtual.university.util.EntityUtils;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class EditTeacherController {
    private final TeacherRepository teacherRepository;

    @Autowired
    public EditTeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @ModelAttribute("teacher")
    public Teacher teacher(@PathVariable int teacherId) {
        return retrieveOneOrThrowNotFound(teacherRepository::findOne, teacherId, Teacher.class);
    }

    @GetMapping("/edit-teacher/{teacherId}")
    public String getTeacherToEdit() {
        return "teacher/edit-teacher";
    }

    @PostMapping("/edit-teacher/{teacherId}")
    public String updateTeacher(
            @ModelAttribute("teacher") Teacher teacher,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "teacher/edit-teacher";
        }
        teacherRepository.save(teacher);
        return "redirect:/teachers";
    }
}
