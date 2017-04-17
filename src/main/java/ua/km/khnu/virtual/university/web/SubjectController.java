package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Subject;

/**
 * @author igorek2312
 */
@RestController
public class SubjectController {
    @PostMapping("/subjects")
    @ResponseStatus(HttpStatus.CREATED)
    public Subject create() {
        return null;
    }

    @GetMapping("/subjects")
    public Page<Subject> getAll(Pageable pageable) {
        return null;
    }

    @PutMapping("/subjects/{subjectId}")
    public Subject update(@PathVariable int subjectId, @RequestBody Subject subject) {
        return null;
    }

    @DeleteMapping("/subjects/{subjectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int subjectId) {
    }
}