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
    private Group group;
    private Teacher teacher;
    private SubjectInstance subjectInstance;

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
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToOne
    @JoinColumn(name = "subject_instance_id", referencedColumnName = "id", nullable = false)
    public SubjectInstance getSubjectInstance() {
        return subjectInstance;
    }

    public void setSubjectInstance(SubjectInstance subjectInstance) {
        this.subjectInstance = subjectInstance;
    }
}
