package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> getAll(
            @RequestParam(value="document-number",required = false) String documentNumber,
            Pageable pageable
    ) {
        if (documentNumber!=null) {
           Teacher teacher= teacherService.getByDocument(documentNumber);
           return new ResponseEntity<>(teacher,HttpStatus.OK);
        }

        Page<Teacher> page = teacherService.getAll(pageable);
        return new ResponseEntity<>(page,HttpStatus.OK);
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
