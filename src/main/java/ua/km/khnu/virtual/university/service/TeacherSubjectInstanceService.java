package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;
import ua.km.khnu.virtual.university.transfare.CreateTeacherSubjectInstanceForm;
import ua.km.khnu.virtual.university.transfare.UpdateTeacherSubjectInstanceForm;


/**
 * @author Igor Rybak
 */
public interface TeacherSubjectInstanceService {

    TeacherSubjectInstance create(CreateTeacherSubjectInstanceForm form);

    Page<TeacherSubjectInstance> getAll(Pageable pageable);

    TeacherSubjectInstance update(int teacherSubjectInstancesId, UpdateTeacherSubjectInstanceForm form);

    void delete(int teacherSubjectInstancesId);
}
