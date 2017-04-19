package ua.km.khnu.virtual.university.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Student;

/**
 * @author igorek2312
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByGroupId(int groupId, Pageable pageable);
}

