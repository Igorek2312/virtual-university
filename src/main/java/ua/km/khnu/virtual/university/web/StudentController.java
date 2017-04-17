package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Student;

/**
 * @author igorek2312
 */
@RestController
public class StudentController {

    @PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public Student create() {
        return null;
    }

    @GetMapping("/students")
    public Page<Student> getAll(Pageable pageable) {
        return null;
    }

    @GetMapping("/groups/{groupId}/students")
    public Page<Student> getByGroup(@PathVariable int groupId) {
        return null;
    }

    @PutMapping("/students/{studentId}")
    public Student update(@PathVariable int studentId, @RequestBody Student student) {
        return null;
    }

    @DeleteMapping("/students/{studentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int studentId) {
    }
}
