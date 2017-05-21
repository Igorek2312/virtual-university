package ua.km.khnu.virtual.university.transfare.legacy;

import org.hibernate.validator.constraints.Range;
import ua.km.khnu.virtual.university.refrence.OddEvenWeek;

import java.time.DayOfWeek;

/**
 * @author Igor Rybak
 */
public class CreatePeriodForm {
    private DayOfWeek dayOfWeek;
    private OddEvenWeek oddEven;
    @Range(min = 1, max = 10)
    private byte periodNumber;
    private int classroomId;
    private int teacherSubjectInstanceId;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public OddEvenWeek getOddEven() {
        return oddEven;
    }

    public void setOddEven(OddEvenWeek oddEven) {
        this.oddEven = oddEven;
    }

    public byte getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(byte periodNumber) {
        this.periodNumber = periodNumber;
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
