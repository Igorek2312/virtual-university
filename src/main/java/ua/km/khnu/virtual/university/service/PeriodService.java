package ua.km.khnu.virtual.university.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ua.km.khnu.virtual.university.model.Period;
import ua.km.khnu.virtual.university.transfare.legacy.CreatePeriodForm;
import ua.km.khnu.virtual.university.transfare.legacy.UpdatePeriodForm;

/**
 * @author Igor Rybak
 */
public interface PeriodService {
    Period create(CreatePeriodForm form);

    Page<Period> getAll(Pageable pageable);

    Page<Period> getByGroup(int groupId, Pageable pageable);

    Page<Period> getByTeacher(int teacherId, Pageable pageable);

    Period update(int periodId, UpdatePeriodForm form);

    void delete(int periodId);
}
