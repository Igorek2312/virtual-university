package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Subject;

/**
 * @author Igor Rybak
 */
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
