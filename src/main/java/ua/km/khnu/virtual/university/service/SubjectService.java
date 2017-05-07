package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Subject;
import ua.km.khnu.virtual.university.transfare.SubjectForm;


/**
 * @author Igor Rybak
 */
public interface SubjectService {

    Subject create(SubjectForm form);

    Page<Subject> getAll(Pageable pageable);

    Subject update(int subjectId, SubjectForm form);

    void delete(int subjectId);
}
