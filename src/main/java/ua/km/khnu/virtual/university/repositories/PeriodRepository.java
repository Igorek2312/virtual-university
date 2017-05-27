package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.km.khnu.virtual.university.model.Period;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Igor Rybak
 */
public interface PeriodRepository extends JpaRepository<Period, Integer> {
    @Query("select p from Period p where p.teacherSubjectInstance.subjectInstance.group.id = ?1")
    Page<Period> findByGroupId(int groupId, Pageable pageable);

    @Query("select p from Period p where p.teacherSubjectInstance.teacher.id = ?1")
    Page<Period> findByTeacherId(int teacherId, Pageable pageable);

    @Query("select p from Period p where " +
            "p.teacherSubjectInstance.subjectInstance.dateBegin >= :dateBegin and " +
            "p.teacherSubjectInstance.subjectInstance.dateEnd <= :dateEnd and " +
            "p.teacherSubjectInstance.subjectInstance.group.id = :groupId " +
            "order by p.dayOfWeek, p.periodNumber, p.oddEven desc"
    )
    List<Period> findByGroupAndSemester(
            @Param("groupId") int groupId,
            @Param("dateBegin") LocalDate dateBegin,
            @Param("dateEnd") LocalDate dateEnd
    );
}
