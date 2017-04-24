package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.PeriodInstance;
import ua.km.khnu.virtual.university.transfare.CreatePeriodInstanceForm;
import ua.km.khnu.virtual.university.transfare.UpdatePeriodInstanceForm;

/**
 * @author Igor Rybak
 */
public interface PeriodInstanceService {
    PeriodInstance create(CreatePeriodInstanceForm form);

    Page<PeriodInstance> getAll(Pageable pageable);

    PeriodInstance update(int periodInstanceId, UpdatePeriodInstanceForm form);

    void delete(int periodInstanceId);

}
