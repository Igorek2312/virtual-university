package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Student;

import java.util.Optional;

/**
 * @author Igor Rybak
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByGroupId(int groupId, Pageable pageable);

    Optional<Student> findByAccountDocumentNumber(String documentNumber);
}

