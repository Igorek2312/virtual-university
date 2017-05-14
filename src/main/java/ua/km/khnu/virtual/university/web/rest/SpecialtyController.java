package ua.km.khnu.virtual.university.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.service.SpecialtyService;
import ua.km.khnu.virtual.university.transfare.CreateSpecialtyForm;

/**
 * @author Igor Rybak
 */
@RestController
@RequestMapping("/")
public class SpecialtyController {
    private SpecialtyService specialtyService;

    @Autowired
    public void setSpecialtyService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @PostMapping("/specialties")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Specialty create(@RequestBody CreateSpecialtyForm form) {
        return specialtyService.create(form);
    }

    @GetMapping("/faculties/{facultyId}/specialties")
    public Page<Specialty> getByFaculty(@PathVariable int facultyId, Pageable pageable) {
        return specialtyService.getByFaculty(facultyId, pageable);
    }

    @GetMapping("/specialties/{specialtyId}")
    public Specialty get(@PathVariable int specialtyId) {
        return specialtyService.get(specialtyId);
    }

    @GetMapping("/specialties")
    public Page<Specialty> getAll(Pageable pageable) {
        return specialtyService.getAll(pageable);
    }

    @PutMapping("/specialties/{specialtyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Specialty update(@PathVariable int specialtyId, @RequestBody CreateSpecialtyForm form) {
        return specialtyService.update(form, specialtyId);
    }

    @DeleteMapping("/specialties/{specialtyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable int specialtyId) {
        specialtyService.delete(specialtyId);
    }
}
