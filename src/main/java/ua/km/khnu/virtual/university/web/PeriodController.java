package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Period;
import ua.km.khnu.virtual.university.service.PeriodService;
import ua.km.khnu.virtual.university.transfare.CreatePeriodForm;
import ua.km.khnu.virtual.university.transfare.UpdatePeriodForm;

/**
 * @author Igor Rybak
 */
@RestController
public class PeriodController {
    private PeriodService periodService;

    @Autowired
    public void setPeriodService(PeriodService periodService) {
        this.periodService = periodService;
    }

    @PostMapping("/periods")
    @ResponseStatus(HttpStatus.CREATED)
    public Period create(CreatePeriodForm form) {
        return periodService.create(form);
    }

    @GetMapping("/periods")
    public Page<Period> getAll(Pageable pageable) {
        return periodService.getAll(pageable);
    }

    @GetMapping("/groups/{groupId}/periods")
    public Page<Period> getByGroup(@PathVariable int groupId, Pageable pageable) {
        return periodService.getByGroup(groupId, pageable);
    }

    @GetMapping("/teachers/{teacherId}/periods")
    public Page<Period> getByTeacher(@PathVariable int teacherId, Pageable pageable) {
        return periodService.getByTeacher(teacherId, pageable);
    }

    @PutMapping("/periods/{periodId}")
    public Period update(@PathVariable int periodId, @RequestBody UpdatePeriodForm form) {
        return periodService.update(periodId,form);
    }

    @DeleteMapping("/periods/{periodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int periodId){
        periodService.delete(periodId);
    }
}
