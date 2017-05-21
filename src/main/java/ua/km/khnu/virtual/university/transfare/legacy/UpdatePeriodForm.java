package ua.km.khnu.virtual.university.transfare.legacy;

import java.time.DayOfWeek;

/**
 * @author Igor Rybak
 */
public class UpdatePeriodForm {
    private DayOfWeek dayOfWeek;
    private String oddEven;
    private byte periodNumber;
    private String defaultType;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getOddEven() {
        return oddEven;
    }

    public void setOddEven(String oddEven) {
        this.oddEven = oddEven;
    }

    public byte getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(byte periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }
}
