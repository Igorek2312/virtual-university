package ua.km.khnu.virtual.university.web.classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.km.khnu.virtual.university.model.Classroom;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.repositories.ClassroomRepository;

/**
 * @author Igor Rybak
 */
@RestController
public class ClassroomAjaxController {
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomAjaxController(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @GetMapping("/search-classrooms")
    public Page<Classroom> searchSubjects(@RequestParam String name, Pageable pageable) {
        return classroomRepository.findByNameLike("%" + name + "%", pageable);
    }
}
