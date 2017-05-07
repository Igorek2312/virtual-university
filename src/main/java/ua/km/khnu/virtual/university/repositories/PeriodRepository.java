package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.km.khnu.virtual.university.model.Period;

/**
 * @author Igor Rybak
 */
public interface PeriodRepository extends JpaRepository<Period, Integer> {
    @Query("select p from Period p where p.teacherSubjectInstance.subjectInstance.group.id = ?1")
    Page<Period> findByGroupId(int groupId, Pageable pageable);

    @Query("select p from Period p where p.teacherSubjectInstance.teacher.id = ?1")
    Page<Period> findByTeacherId(int teacherId, Pageable pageable);
}
