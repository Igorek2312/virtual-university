package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.km.khnu.virtual.university.model.SubjectInstance;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Igor Rybak
 */
public interface SubjectInstanceRepository extends JpaRepository<SubjectInstance, Integer> {
    List<SubjectInstance> findByGroupId(int groupId, Sort sort);

    @Query("select si from SubjectInstance as si where " +
            "si.dateBegin >= :dateBegin and si.dateEnd <= :dateEnd and si.group.id=:groupId")
    List<SubjectInstance> findByGroupAndSemester(
            @Param("groupId") int groupId,
            @Param("dateBegin") LocalDate dateBegin,
            @Param("dateEnd") LocalDate dateEnd
    );

    @Query("select year(s.dateBegin), case when (month(s.dateBegin)>8) then 1 else 2 end from SubjectInstance s")
    List<Object[]> findSemesters();
}
