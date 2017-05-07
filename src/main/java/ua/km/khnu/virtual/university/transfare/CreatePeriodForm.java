package ua.km.khnu.virtual.university.transfare;

import java.time.DayOfWeek;

/**
 * @author Igor Rybak
 */
public class CreatePeriodForm {
    private DayOfWeek dayOfWeek;
    private String oddEven;
    private byte periodNumber;
    private String defaultType;
    private int classroomId;
    private int teacherSubjectInstanceId;

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

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public int getTeacherSubjectInstanceId() {
        return teacherSubjectInstanceId;
    }

    public void setTeacherSubjectInstanceId(int teacherSubjectInstanceId) {
        this.teacherSubjectInstanceId = teacherSubjectInstanceId;
    }
}
