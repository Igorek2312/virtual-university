package ua.km.khnu.virtual.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Faculty;

/**
 * @author Igor Rybak
 */
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
}
