package ua.km.khnu.virtual.university.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.km.khnu.virtual.university.model.PeriodInstance;

import java.time.LocalDate;

/**
 * @author Igor Rybak
 */
public interface PeriodInstanceRepository extends JpaRepository<PeriodInstance,Integer>{
    boolean existsByDateAndPeriodId(LocalDate date,Integer periodId);

    void deleteAllByPeriodId(Integer periodId);
}
