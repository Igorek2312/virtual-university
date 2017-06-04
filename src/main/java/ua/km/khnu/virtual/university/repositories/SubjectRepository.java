package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.km.khnu.virtual.university.model.Subject;

/**
 * @author Igor Rybak
 */
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Page<Subject> findByNameLike(String name, Pageable pageable);
}
