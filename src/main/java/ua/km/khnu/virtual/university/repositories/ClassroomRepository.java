package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Classroom;
import ua.km.khnu.virtual.university.model.Subject;

/**
 * @author Igor Rybak
 */
public interface ClassroomRepository extends JpaRepository<Classroom,Integer> {
    Page<Classroom> findByNameLike(String s, Pageable pageable);
}
