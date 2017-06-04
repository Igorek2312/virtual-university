package ua.km.khnu.virtual.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;
import ua.km.khnu.virtual.university.refrence.OddEvenWeek;

import javax.persistence.*;

/**
 * @author Igor Rybak
 */
@Entity
public class Period {
    private Integer id;
    @Range(min = 1,max = 6)
    private byte dayOfWeek;
    private OddEvenWeek oddEven;
    @Range(min = 1,max = 8)
    private byte periodNumber;
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

    @Column(name = "day_of_week", columnDefinition = "TINYINT", nullable = false)
    public byte getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(byte dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Column(name = "odd_even", nullable = false)
    @Enumerated(EnumType.STRING)
    public OddEvenWeek getOddEven() {
        return oddEven;
    }

    public void setOddEven(OddEvenWeek oddEven) {
        this.oddEven = oddEven;
    }

    @Column(name = "period_number", nullable = false)
    public byte getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(byte periodNumber) {
        this.periodNumber = periodNumber;
    }

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
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
