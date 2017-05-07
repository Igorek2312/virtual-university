package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.service.SubjectInstanceService;
import ua.km.khnu.virtual.university.transfare.CreateSubjectInstanceForm;
import ua.km.khnu.virtual.university.transfare.UpdateSubjectInstanceForm;

/**
 * @author Igor Rybak
 */
@RestController
public class SubjectInstanceController {
    private SubjectInstanceService subjectInstanceService;

    @Autowired
    public void setSubjectInstanceService(SubjectInstanceService subjectInstanceService) {
        this.subjectInstanceService = subjectInstanceService;
    }

    @PostMapping("/subject-instances")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SubjectInstance create(@RequestBody CreateSubjectInstanceForm form) {
        return subjectInstanceService.create(form);
    }

    @GetMapping("/subject-instances")
    public Page<SubjectInstance> getAll(Pageable pageable) {
        return subjectInstanceService.getAll(pageable);
    }

    @PutMapping("/subject-instances/{subjectInstanceId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public SubjectInstance update(
            @PathVariable int subjectInstanceId,
            @RequestBody UpdateSubjectInstanceForm form
    ) {
        return subjectInstanceService.update(subjectInstanceId, form);
    }

    @DeleteMapping("/subject-instances/{subjectInstanceId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int subjectInstanceId) {
        subjectInstanceService.delete(subjectInstanceId);
    }
}
