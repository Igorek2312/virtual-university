package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.SubjectInstance;

/**
 * @author Igor Rybak
 */
public interface SubjectInstanceRepository extends JpaRepository<SubjectInstance, Integer>{
}
