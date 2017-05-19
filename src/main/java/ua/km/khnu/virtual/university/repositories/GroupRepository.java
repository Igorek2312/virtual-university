package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.Group;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> findBySpecialtyId(int specialtyId, Sort sort);
}
