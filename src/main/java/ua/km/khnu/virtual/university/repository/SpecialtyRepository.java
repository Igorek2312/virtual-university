package ua.km.khnu.virtual.university.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.km.khnu.virtual.university.model.Specialty;


/**
 * @author Igor Rybak
 */
@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
    Page<Specialty> findByFacultyId(int facultyId, Pageable pageable);
}
