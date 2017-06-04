package ua.km.khnu.virtual.university.web.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.km.khnu.virtual.university.model.Teacher;
import ua.km.khnu.virtual.university.repositories.TeacherRepository;

/**
 * @author Igor Rybak
 */
@RestController
public class TeacherAjaxController {
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherAjaxController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @GetMapping("/search-teachers")
    public Page<Teacher> searchTeachers(@RequestParam String name, Pageable pageable) {
        return teacherRepository.findByFullName("%" + name + "%", pageable);
    }
}
