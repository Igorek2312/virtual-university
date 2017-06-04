package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.km.khnu.virtual.university.model.Teacher;

/**
 * @author Igor Rybak
 */
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query("select t from Teacher t where " +
            "t.account.firstName like :name or " +
            "t.account.lastName like :name or " +
            "t.account.middleName like :name")
    Page<Teacher> findByFullName(@Param("name") String name, Pageable pageable);
}
