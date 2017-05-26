package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.km.khnu.virtual.university.model.Student;
import ua.km.khnu.virtual.university.repositories.StudentRepository;
import ua.km.khnu.virtual.university.service.SubjectInstanceService;
import ua.km.khnu.virtual.university.transfare.SemesterDto;
import ua.km.khnu.virtual.university.util.SecurityUtils;

import java.util.List;

/**
 * @author Igor Rybak
 */
@Controller
public class StudentLessonsScheduleController {
    private final SubjectInstanceService subjectInstanceService;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentLessonsScheduleController(SubjectInstanceService subjectInstanceService, StudentRepository studentRepository) {
        this.subjectInstanceService = subjectInstanceService;
        this.studentRepository = studentRepository;
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping("/semesters")
    public String getSemestersOfStudent(
            Model model
    ) {
        String login = SecurityUtils.getCurrentUserLogin();
        Student student = studentRepository.findByAccountUsername(login);
        Integer groupId = student.getGroup().getId();

        List<SemesterDto> semesters = subjectInstanceService.getSemesters(groupId);
        model.addAttribute("semesters", semesters);
        model.addAttribute("groupId", groupId);

        return "subject/instance/semesters-of-group";
    }

}
