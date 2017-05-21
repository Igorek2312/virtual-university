package ua.km.khnu.virtual.university.transfare;

/**
 * @author Igor Rybak
 */
public class SemesterDto {
    private int year;
    private int semesterNumber;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SemesterDto that = (SemesterDto) o;

        if (year != that.year) return false;
        return semesterNumber == that.semesterNumber;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + semesterNumber;
        return result;
    }
}
