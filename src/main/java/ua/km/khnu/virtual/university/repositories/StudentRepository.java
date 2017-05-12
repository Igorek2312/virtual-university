package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Student;

/**
 * @author Igor Rybak
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByGroupId(int groupId, Pageable pageable);
    Page<Student> findByGroupName(String groupName, Pageable pageable);
}

