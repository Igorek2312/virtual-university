package ua.km.khnu.virtual.university.transfare.legacy;

/**
 * @author Igor Rybak
 */
public class CreateGradeForm {
    private Byte value;
    private boolean present;
    private int periodInstanceId;
    private int studentId;

    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    public int getPeriodInstanceId() {
        return periodInstanceId;
    }

    public void setPeriodInstanceId(int periodInstanceId) {
        this.periodInstanceId = periodInstanceId;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
