package ua.km.khnu.virtual.university.model;

import javax.persistence.*;
import java.time.DayOfWeek;

/**
 * @author Igor Rybak
 */
@Entity
public class Period {
    private Integer id;
    private DayOfWeek dayOfWeek;
    private String oddEven;
    private byte periodNumber;
    private String defaultType;
    private Classroom classroom;
    private TeacherSubjectInstance teacherSubjectInstance;

    public Period() {
    }

    public Period(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "day_of_week", columnDefinition = "TINYINT")
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Column(name = "odd_even")
    public String getOddEven() {
        return oddEven;
    }

    public void setOddEven(String oddEven) {
        this.oddEven = oddEven;
    }

    @Column(name = "period_number")
    public byte getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(byte periodNumber) {
        this.periodNumber = periodNumber;
    }

    @Column(name = "default_type")
    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id", nullable = false)
    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_subject_instance_id", referencedColumnName = "id", nullable = false)
    public TeacherSubjectInstance getTeacherSubjectInstance() {
        return teacherSubjectInstance;
    }

    public void setTeacherSubjectInstance(TeacherSubjectInstance teacherSubjectInstance) {
        this.teacherSubjectInstance = teacherSubjectInstance;
    }
}
