package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Classroom;

/**
 * @author Igor Rybak
 */
public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {
}
