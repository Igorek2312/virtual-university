package ua.km.khnu.virtual.university.refrence;

import java.time.LocalDate;

/**
 * @author Igor Rybak
 */
public class Semester {
    private final int mouthBegin;
    private final int mouthEnd;
    private final int year;

    public Semester(int year, int semesterNumber) {
        this.year = year;
        mouthBegin = semesterNumber == 1 ? 9 : 1;//january or september
        mouthEnd = semesterNumber == 1 ? 12 : 8;//december or august
    }

    public LocalDate getDateBegin() {
        return LocalDate.of(year, mouthBegin, 1);
    }

    public LocalDate getDateEnd() {
        return LocalDate.of(year, mouthEnd, 31);
    }
}
