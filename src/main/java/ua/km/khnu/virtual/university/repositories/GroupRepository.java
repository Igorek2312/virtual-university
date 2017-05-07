package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Group;

/**
 * @author Igor Rybak
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Page<Group> findBySpecialtyId(int specialtyId, Pageable pageable);
}
