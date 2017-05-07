package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.transfare.CreateSubjectInstanceForm;
import ua.km.khnu.virtual.university.transfare.UpdateSubjectInstanceForm;

/**
 * @author Igor Rybak
 */
public interface SubjectInstanceService {
    SubjectInstance create(CreateSubjectInstanceForm form);

    Page<SubjectInstance> getAll(Pageable pageable);

    SubjectInstance update(int subjectInstanceId, UpdateSubjectInstanceForm form);

    void delete(int subjectInstanceId);
}
