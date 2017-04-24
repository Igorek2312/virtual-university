package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.km.khnu.virtual.university.model.Grade;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface GradeRepository extends JpaRepository<Grade, Integer> {
    @Query("select g from  Grade g where g.periodInstance.period.teacherSubjectInstance.subjectInstance.id = ?1")
    List<Grade> findBySubjectInstanceId(int subjectInstanceId);
}
