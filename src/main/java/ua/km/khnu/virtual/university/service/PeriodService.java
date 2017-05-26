package ua.km.khnu.virtual.university.service;

import ua.km.khnu.virtual.university.model.Period;

import java.util.List;

/**
 * @author Igor Rybak
 */
public interface PeriodService {
    void save(Period period);

    List<Period> periodsSchedule(int groupId, int year, int semester);

    void delete(int periodId);

    Period get(int periodId);
}
