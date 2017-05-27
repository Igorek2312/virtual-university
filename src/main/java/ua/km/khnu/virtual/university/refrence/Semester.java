package ua.km.khnu.virtual.university.refrence;

import java.time.LocalDate;

/**
 * @author Igor Rybak
 */
public class Semester {
    private final int year;
    private final int semesterNumber;

    public Semester(LocalDate dateBegin) {
        year = dateBegin.getYear();
        semesterNumber = dateBegin.getMonth().getValue() > 6 ? 1 : 2;
    }

    public int getYear() {
        return year;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Semester semester = (Semester) o;

        if (year != semester.year) return false;
        return semesterNumber == semester.semesterNumber;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + semesterNumber;
        return result;
    }
}
