package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Faculty;

/**
 * @author Igor Rybak
 */
@Transactional
public interface FacultyRepository extends JpaRepository<Faculty,Integer> {
}
