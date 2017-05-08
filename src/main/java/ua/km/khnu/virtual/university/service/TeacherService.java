package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Teacher;
import ua.km.khnu.virtual.university.transfare.TeacherForm;

/**
 * @author Igor Rybak
 */
public interface TeacherService {
    Teacher create(TeacherForm form);

    Page<Teacher> getAll(Pageable pageable);

    Teacher update(int teacherId, TeacherForm form);

    void delete(int teacherId);

}
