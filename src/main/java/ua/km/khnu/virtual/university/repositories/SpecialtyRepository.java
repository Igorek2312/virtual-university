package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.km.khnu.virtual.university.model.Specialty;

import java.util.List;


/**
 * @author Igor Rybak
 */
@Repository
@Transactional
public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
    List<Specialty> findByFacultyId(int facultyId);
}
