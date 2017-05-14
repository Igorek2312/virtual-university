package ua.km.khnu.virtual.university.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Teacher;
import ua.km.khnu.virtual.university.service.TeacherService;
import ua.km.khnu.virtual.university.transfare.TeacherForm;

/**
 * @author Igor Rybak
 */
@RestController
public class TeacherController {
    private TeacherService teacherService;

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/teachers")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Teacher create(@RequestBody TeacherForm form) {
        return teacherService.create(form);
    }

    @GetMapping("/teachers")
    public Page<Teacher> getAll(Pageable pageable) {
        return teacherService.getAll(pageable);
    }

    @PutMapping("/teachers/{teacherId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Teacher update(@PathVariable int teacherId, @RequestBody TeacherForm form) {
        return teacherService.update(teacherId, form);
    }

    @DeleteMapping("/teachers/{teacherId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int teacherId) {
        teacherService.delete(teacherId);
    }
}
