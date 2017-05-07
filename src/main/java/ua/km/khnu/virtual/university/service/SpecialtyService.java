package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Specialty;
import ua.km.khnu.virtual.university.transfare.CreateSpecialtyForm;

/**
 * @author Igor Rybak
 */
public interface SpecialtyService {
    Specialty create(CreateSpecialtyForm form);

    Specialty get(int id);

    Page<Specialty> getAll(Pageable pageable);

    Specialty update(CreateSpecialtyForm form, int id);

    void delete(int id);

    Page<Specialty> getByFaculty(int facultyId, Pageable pageable);
}
