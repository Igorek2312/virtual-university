package ua.km.khnu.virtual.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.km.khnu.virtual.university.model.Faculty;
import ua.km.khnu.virtual.university.service.FacultyService;
import ua.km.khnu.virtual.university.transfare.FacultyForm;

import static ua.km.khnu.virtual.university.refrence.UriConstants.FACULTIES;

/**
 * @author Igor Rybak
 */
@RestController
@RequestMapping(FACULTIES)
public class FacultyController {
    private FacultyService facultyService;

    @Autowired
    public void setFacultyService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Faculty create(@RequestBody FacultyForm facultyForm) {
        return facultyService.createFaculty(facultyForm);
    }

    @GetMapping("/{facultyId}")
    public Faculty getOne(@PathVariable Integer facultyId) {
        return facultyService.get(facultyId);
    }

    @GetMapping
    public Page<Faculty> getAllFaculties(Pageable pageable) {
        return facultyService.getAll(pageable);
    }

    @PatchMapping("/{facultyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Faculty update(@RequestBody FacultyForm facultyForm, @PathVariable Integer facultyId) {
        return facultyService.update(facultyForm, facultyId);
    }

    @DeleteMapping("/{facultyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer facultyId) {
        facultyService.delete(facultyId);
    }
}