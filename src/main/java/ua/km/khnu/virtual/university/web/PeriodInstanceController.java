package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.PeriodInstance;

/**
 * @author igorek2312
 */
@RestController
public class PeriodInstanceController {
    @PostMapping("/period-instances")
    @ResponseStatus(HttpStatus.CREATED)
    public PeriodInstance create() {
        return null;
    }

    @GetMapping("/period-instances")
    public Page<PeriodInstance> getAll(Pageable pageable) {
        return null;
    }

    @PutMapping("/period-instances/{periodInstanceId}")
    public PeriodInstance update(
            @PathVariable int periodInstanceId,
            @RequestBody PeriodInstance periodInstance
    ) {
        return null;
    }

    @DeleteMapping("/period-instances/{periodInstanceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int periodInstanceId) {
    }
}
