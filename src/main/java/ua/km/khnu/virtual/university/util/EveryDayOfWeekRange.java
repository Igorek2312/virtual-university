package ua.km.khnu.virtual.university.util;

import ua.km.khnu.virtual.university.refrence.OddEvenWeek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * @author Igor Rybak
 */
public class EveryDayOfWeekRange implements Iterable<LocalDate> {
    private LocalDate begin;
    private LocalDate end;
    private OddEvenWeek oddEvenWeek;
    private DayOfWeek dayOfWeek;

    public EveryDayOfWeekRange(LocalDate begin, LocalDate end, OddEvenWeek oddEvenWeek, DayOfWeek dayOfWeek) {
        this.begin = begin;
        this.end = end;
        this.oddEvenWeek = oddEvenWeek;
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new EveryDayOfWeekIterator();
    }


    private class EveryDayOfWeekIterator implements Iterator<LocalDate> {
        private LocalDate current;

        public EveryDayOfWeekIterator() {
            current = (LocalDate) dayOfWeek.adjustInto(begin);
            if (oddEvenWeek == OddEvenWeek.EVEN) {
                current = current.plusDays(7);
            }
        }

        @Override
        public boolean hasNext() {
            return current.isBefore(end);
        }

        @Override
        public LocalDate next() {
            LocalDate result = current;
            current = current.plusDays(oddEvenWeek == OddEvenWeek.BOTH ? 7 : 14);
            return result;
        }
    }


}
