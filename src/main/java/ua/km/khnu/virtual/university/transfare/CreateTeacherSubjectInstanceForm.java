package ua.km.khnu.virtual.university.transfare;

/**
 * @author Igor Rybak
 */
public class CreateTeacherSubjectInstanceForm {
    private String periodType;
    private int teacherId;
    private int subjectInstanceId;

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getSubjectInstanceId() {
        return subjectInstanceId;
    }

    public void setSubjectInstanceId(int subjectInstanceId) {
        this.subjectInstanceId = subjectInstanceId;
    }
}
