package ua.km.khnu.virtual.university.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.service.SubjectService;
import ua.km.khnu.virtual.university.transfare.SubjectForm;

/**
 * @author Igor Rybak
 */
@RestController
public class SubjectController {
    private SubjectService subjectService;

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/subjects")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Subject create(@RequestBody SubjectForm form) {
        return subjectService.create(form);
    }

    @GetMapping("/subjects")
    public Page<Subject> getAll(Pageable pageable) {
        return subjectService.getAll(pageable);
    }

    @PutMapping("/subjects/{subjectId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Subject update(@PathVariable int subjectId, @RequestBody SubjectForm form) {
        return subjectService.update(subjectId, form);
    }

    @DeleteMapping("/subjects/{subjectId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int subjectId) {
        subjectService.delete(subjectId);
    }
}