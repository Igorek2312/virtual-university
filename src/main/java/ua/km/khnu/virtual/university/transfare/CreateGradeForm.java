package ua.km.khnu.virtual.university.transfare;

/**
 * @author Igor Rybak
 */
public class CreateGradeForm {
    private byte value;
    private int periodInstanceId;

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public int getPeriodInstanceId() {
        return periodInstanceId;
    }

    public void setPeriodInstanceId(int periodInstanceId) {
        this.periodInstanceId = periodInstanceId;
    }
}
