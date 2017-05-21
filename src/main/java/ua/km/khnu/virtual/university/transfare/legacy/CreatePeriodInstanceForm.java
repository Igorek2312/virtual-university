package ua.km.khnu.virtual.university.transfare.legacy;

import java.time.LocalDate;

/**
 * @author Igor Rybak
 */
public class CreatePeriodInstanceForm {
    private String type;
    private LocalDate date;
    private int periodId;

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

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }
}
