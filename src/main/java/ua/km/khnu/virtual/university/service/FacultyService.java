package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Faculty;
import ua.km.khnu.virtual.university.transfare.FacultyForm;

/**
 * @author Igor Rybak
 */
public interface FacultyService {
    Faculty createFaculty(FacultyForm faculty);

    Page<Faculty> getAll(Pageable pageable);

    Faculty get(int id);

    Faculty update(FacultyForm faculty, int id);

    void delete(int id);
}
