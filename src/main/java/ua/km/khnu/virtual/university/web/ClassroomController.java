package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Classroom;

/**
 * @author igorek2312
 */
@RestController
public class ClassroomController {

    @PostMapping("/classrooms")
    @ResponseStatus(HttpStatus.CREATED)
    public Classroom create() {
        return null;
    }

    @GetMapping("/classrooms")
    public Page<Classroom> getAll(Pageable pageable) {
        return null;
    }

    @PutMapping("/classrooms/{classroomId}")
    public Classroom update(@PathVariable int classroomId, @RequestBody Classroom classroom) {
        return null;
    }

    @DeleteMapping("/classrooms/{classroomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int classroomId) {
    }

}
