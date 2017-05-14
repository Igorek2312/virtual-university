package ua.km.khnu.virtual.university.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Classroom;
import ua.km.khnu.virtual.university.service.ClassroomService;
import ua.km.khnu.virtual.university.transfare.ClassroomForm;

/**
 * @author Igor Rybak
 */
@RestController
public class ClassroomController {
    private ClassroomService classroomService;

    @Autowired
    public void setClassroomService(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping("/classrooms")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Classroom create(@RequestBody ClassroomForm form) {
        return classroomService.create(form);
    }

    @GetMapping("/classrooms")
    public Page<Classroom> getAll(Pageable pageable) {
        return classroomService.getAll(pageable);
    }

    @PutMapping("/classrooms/{classroomId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Classroom update(@PathVariable int classroomId, @RequestBody ClassroomForm form) {
        return classroomService.update(classroomId,form);
    }

    @DeleteMapping("/classrooms/{classroomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int classroomId) {
        classroomService.delete(classroomId);
    }

}
