package ua.km.khnu.virtual.university.web.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.repositories.StudentRepository;

import static ua.km.khnu.virtual.university.util.EntityUtils.retrieveOneOrThrowNotFound;

/**
 * @author Igor Rybak
 */
@Controller
public class EditStudentController {
    private final StudentRepository studentRepository;

    @Autowired
    public EditStudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @ModelAttribute("student")
    public Student studentToEdit(@PathVariable int studentId) {
        return retrieveOneOrThrowNotFound(studentRepository::findOne, studentId, Student.class);
    }

    @GetMapping("/edit-student/{studentId}")
    public String getStudentToEdit() {
        return "student/edit-student";
    }

    @PostMapping("/edit-student/{studentId}")
    public String updateStudent(
            @ModelAttribute("student") @Validated Student student,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "student/edit-student";
        }

        studentRepository.save(student);
        return "redirect:/groups/" + student.getGroup().getId() + "/students";
    }
}
