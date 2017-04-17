package ua.km.khnu.virtual.university.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Period;

/**
 * @author igorek2312
 */
@RestController
public class PeriodController {
    @PostMapping("/periods")
    @ResponseStatus(HttpStatus.CREATED)
    public Period create() {
        return null;
    }

    @GetMapping("/periods")
    public Page<Period> getAll(Pageable pageable) {
        return null;
    }

    @GetMapping("/groups/{groupId}/periods")
    public Page<Period> getByGroup(@PathVariable int groupId) {
        return null;
    }

    @GetMapping("/teachers/{teacherId}/periods")
    public Page<Period> getByTeacher(@PathVariable int teacherId) {
        return null;
    }

    @PutMapping("/periods/{periodId}")
    public Period update(@PathVariable int periodId) {
        return null;
    }

    @DeleteMapping("/period/{periodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int periodId){

    }
}
