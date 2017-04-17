package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.model.SubjectInstance;

/**
 * @author igorek2312
 */
@RestController
public class SubjectInstanceController {
    @PostMapping("/subject-instances")
    @ResponseStatus(HttpStatus.CREATED)
    public Subject create() {
        return null;
    }

    @GetMapping("/subject-instances")
    public Page<Subject> getAll(Pageable pageable) {
        return null;
    }

    @PutMapping("/subject-instances/{subjectInstanceId}")
    public Subject update(@PathVariable int subjectInstanceId, @RequestBody SubjectInstance subject) {
        return null;
    }

    @DeleteMapping("/subject-instances/{subjectInstanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int subjectInstanceId) {
    }
}
