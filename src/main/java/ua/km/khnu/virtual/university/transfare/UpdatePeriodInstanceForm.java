package ua.km.khnu.virtual.university.transfare;

import java.time.LocalDate;

/**
 * @author Igor Rybak
 */
public class UpdatePeriodInstanceForm {
    private String type;
    private LocalDate date;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
