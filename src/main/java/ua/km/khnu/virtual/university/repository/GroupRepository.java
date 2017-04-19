package ua.km.khnu.virtual.university.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Group;

/**
 * @author igorek2312
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Page<Group> findBySpecialtyId(int specialtyId, Pageable pageable);
}
