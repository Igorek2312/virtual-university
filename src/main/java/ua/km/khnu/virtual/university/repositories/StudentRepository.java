package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * @author Igor Rybak
 */
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByGroupId(int groupId, Sort sort);

    Page<Student> findByGroupName(String groupName, Pageable pageable);

    Optional<Student> findByAccountId(int accountId);

    Student findByAccountUsername(String username);
}

