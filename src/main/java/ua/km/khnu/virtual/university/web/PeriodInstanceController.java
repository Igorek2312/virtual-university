package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.PeriodInstance;
import ua.km.khnu.virtual.university.service.PeriodInstanceService;
import ua.km.khnu.virtual.university.transfare.CreatePeriodInstanceForm;
import ua.km.khnu.virtual.university.transfare.UpdatePeriodInstanceForm;

/**
 * @author Igor Rybak
 */
@RestController
public class PeriodInstanceController {
    private PeriodInstanceService periodInstanceService;

    @Autowired
    public void setPeriodInstanceService(PeriodInstanceService periodInstanceService) {
        this.periodInstanceService = periodInstanceService;
    }

    @PostMapping("/period-instances")
    @ResponseStatus(HttpStatus.CREATED)
    public PeriodInstance create(@RequestBody CreatePeriodInstanceForm form) {
        return periodInstanceService.create(form);
    }

    @PostMapping("periods/{periodId}/period-instances")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAll(@PathVariable int periodId) {
       periodInstanceService.createAll(periodId);
    }

    @GetMapping("/period-instances")
    public Page<PeriodInstance> getAll(Pageable pageable) {
        return periodInstanceService.getAll(pageable);
    }

    @PutMapping("/period-instances/{periodInstanceId}")
    public PeriodInstance update(
            @PathVariable int periodInstanceId,
            @RequestBody UpdatePeriodInstanceForm form
    ) {
        return periodInstanceService.update(periodInstanceId, form);
    }

    @DeleteMapping("/period-instances/{periodInstanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int periodInstanceId) {
        periodInstanceService.delete(periodInstanceId);
    }

    @DeleteMapping("/periods/{periodId}/period-instances")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteAll(@PathVariable int periodId) {
        periodInstanceService.deleteAll(periodId);
    }

}
