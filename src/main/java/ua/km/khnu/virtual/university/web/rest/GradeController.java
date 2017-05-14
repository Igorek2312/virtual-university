package ua.km.khnu.virtual.university.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Grade;
import ua.km.khnu.virtual.university.service.GradeService;
import ua.km.khnu.virtual.university.transfare.CreateGradeForm;
import ua.km.khnu.virtual.university.transfare.UpdateGradeForm;

import java.util.List;

/**
 * @author Igor Rybak
 */
@RestController
public class GradeController {
    private GradeService gradeService;

    @Autowired
    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @PostMapping("/grades")
    @ResponseStatus(HttpStatus.CREATED)
    public Grade create(@RequestBody CreateGradeForm form) {
        return gradeService.create(form);
    }

    @GetMapping("/grades")
    public Page<Grade> getAll(Pageable pageable) {
        return gradeService.getAll(pageable);
    }

    @GetMapping("/subject-instances/{subjectInstance}/grades")
    public List<Grade> getBySubjectInstance(
            @PathVariable int subjectInstanceId
    ) {
        return gradeService.getBySubjectInstance(subjectInstanceId);
    }

    @PutMapping("/grades/{gradeId}")
    public Grade update(@PathVariable int gradeId, @RequestBody UpdateGradeForm form) {
        return gradeService.update(gradeId,form);
    }
}
