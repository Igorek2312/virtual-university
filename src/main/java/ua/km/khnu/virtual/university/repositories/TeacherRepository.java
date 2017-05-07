package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Teacher;

/**
 * @author Igor Rybak
 */
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {

}
