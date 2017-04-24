package ua.km.khnu.virtual.university.transfare;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author Igor Rybak
 */
public class UpdateSubjectInstanceForm {
    private String controlType;
    private String subjectType;
    private LocalDate dateBegin;
    private LocalDate dateEnd;
    private Duration hours;

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Duration getHours() {
        return hours;
    }

    public void setHours(Duration hours) {
        this.hours = hours;
    }
}
