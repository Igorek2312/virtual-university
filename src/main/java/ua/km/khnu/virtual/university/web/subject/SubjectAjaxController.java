package ua.km.khnu.virtual.university.web.subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.model.Teacher;
import ua.km.khnu.virtual.university.repositories.SubjectRepository;

/**
 * @author Igor Rybak
 */
@RestController
public class SubjectAjaxController {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectAjaxController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping("/search-subjects")
    public Page<Subject> searchSubjects(@RequestParam String name, Pageable pageable) {
        return subjectRepository.findByNameLike("%" + name + "%", pageable);
    }
}
