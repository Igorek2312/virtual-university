package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface TeacherSubjectInstanceRepository extends JpaRepository<TeacherSubjectInstance, Integer> {
    List<TeacherSubjectInstance> findBySubjectInstanceId(int subjectInstanceId);
}
