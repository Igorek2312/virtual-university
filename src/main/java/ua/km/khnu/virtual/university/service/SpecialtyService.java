package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.transfare.SpecialtyForm;

/**
 * @author Igor Rybak
 */
public interface SpecialtyService {
    Specialty create(SpecialtyForm form);

    Specialty get(int id);

    Page<Specialty> getAll(Pageable pageable);

    Specialty update(SpecialtyForm form, int id);

    void delete(int id);

    Page<Specialty> getByFaculty(int facultyId, Pageable pageable);
}
