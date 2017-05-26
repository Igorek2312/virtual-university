package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.km.khnu.virtual.university.model.TeacherSubjectInstance;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Igor Rybak
 */
public interface TeacherSubjectInstanceRepository extends JpaRepository<TeacherSubjectInstance, Integer> {
    List<TeacherSubjectInstance> findBySubjectInstanceId(int subjectInstanceId);
    @Query("select t from TeacherSubjectInstance t where " +
            "t.subjectInstance.dateBegin  >= :dateBegin and " +
            "t.subjectInstance.dateEnd <= :dateEnd and " +
            "t.subjectInstance.group.id = :groupId")
    List<TeacherSubjectInstance> findByGroupAndSemester(
            @Param("groupId") int groupId,
            @Param("dateBegin") LocalDate dateBegin,
            @Param("dateEnd") LocalDate dateEnd
    );
}
