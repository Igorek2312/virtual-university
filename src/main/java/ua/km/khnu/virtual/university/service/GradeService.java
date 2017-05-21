package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Grade;
import ua.km.khnu.virtual.university.transfare.legacy.CreateGradeForm;
import ua.km.khnu.virtual.university.transfare.legacy.UpdateGradeForm;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface GradeService {
    Grade create(CreateGradeForm form);

    Page<Grade> getAll(Pageable pageable);

    List<Grade> getBySubjectInstance(int subjectInstanceId);

    Grade update(int gradeId, UpdateGradeForm form);
}
