package ua.km.khnu.virtual.university.transfare;

import ua.km.khnu.virtual.university.model.Period;

import java.util.List;

/**
 * @author Igor Rybak
 */
public class PeriodsOfDayOfWeekDto {
    private int dayOfWeek;

    private List<Period> periods;

    public PeriodsOfDayOfWeekDto(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public PeriodsOfDayOfWeekDto(int dayOfWeek, List<Period> periods) {
        this.dayOfWeek = dayOfWeek;
        this.periods = periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PeriodsOfDayOfWeekDto that = (PeriodsOfDayOfWeekDto) o;

        return dayOfWeek == that.dayOfWeek;
    }

    @Override
    public int hashCode() {
        return dayOfWeek;
    }
}
