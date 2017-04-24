package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Classroom;
import ua.km.khnu.virtual.university.transfare.ClassroomForm;

/**
 * @author Igor Rybak
 */
public interface ClassroomService {
    Classroom create(ClassroomForm form);

    Page<Classroom> getAll(Pageable pageable);

    Classroom update(int classroomId, ClassroomForm form);

    void delete(int classroomId);
}
