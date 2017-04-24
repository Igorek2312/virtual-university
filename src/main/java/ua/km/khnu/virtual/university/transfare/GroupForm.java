package ua.km.khnu.virtual.university.transfare;

import java.time.Year;

/**
 * @author Igor Rybak
 */
public class GroupForm {
    private String name;

    private Year yearEntered;

    private int yearOfStudyEntered;

    private int specialtyId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getYearEntered() {
        return yearEntered;
    }

    public void setYearEntered(Year yearEntered) {
        this.yearEntered = yearEntered;
    }

    public int getYearOfStudyEntered() {
        return yearOfStudyEntered;
    }

    public void setYearOfStudyEntered(int yearOfStudyEntered) {
        this.yearOfStudyEntered = yearOfStudyEntered;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }
}
