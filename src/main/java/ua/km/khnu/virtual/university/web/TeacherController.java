package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Teacher;

/**
 * @author igorek2312
 */
@RestController
public class TeacherController {
    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    public Teacher create() {
        return null;
    }

    @GetMapping("/teachers")
    public Page<Teacher> getAll(Pageable pageable) {
        return null;
    }

    @PutMapping("/teachers/{teacherId}")
    public Teacher update(@PathVariable int teacherId, @RequestBody Teacher Teacher) {
        return null;
    }

    @DeleteMapping("/teachers/{teacherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int teacherId) {
    }
}
