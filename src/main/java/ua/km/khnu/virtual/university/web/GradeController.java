package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Grade;

/**
 * @author igorek2312
 */
@RestController
public class GradeController {
    @PostMapping("/grades")
    @ResponseStatus(HttpStatus.CREATED)
    public Grade create() {
        return null;
    }

    @GetMapping("/grades")
    public Page<Grade> getAll(Pageable pageable) {
        return null;
    }

    @GetMapping("/subject-instances/{subjectInstance}/grades")
    public Page<Grade> getBySubjectInstance(
            @PathVariable int subjectInstance,
            Pageable pageable
    ) {
        return null;
    }

    @PutMapping("/grades/{gradeId}")
    public Grade update(@PathVariable int gradeId, @RequestBody Grade grade) {
        return null;
    }

    @DeleteMapping("/grades/{gradeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int gradeId) {

    }
}
