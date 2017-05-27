package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.SubjectInstance;
import ua.km.khnu.virtual.university.refrence.Semester;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface SubjectInstanceService {
    SubjectInstance prepareNew();

    SubjectInstance create(SubjectInstance subjectInstance, int groupId, int subjectId);

    Page<SubjectInstance> getByGroupAndSemester(Pageable pageable, int year, int semester);

    List<Semester> getSemesters(int groupId);

    void delete(int subjectInstanceId);

    List<SubjectInstance> getBySemester(int groupId, int year, int semesterNumber);

    SubjectInstance get(int subjectInstanceId);
}
