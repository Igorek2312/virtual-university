package ua.km.khnu.virtual.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * @author Igor Rybak
 */
@Entity
public class Grade {
    private Integer id;
    private Byte value;
    private boolean present;
    private PeriodInstance periodInstance;
    private Student student;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "value")
    public Byte getValue() {
        return value;
    }

    public void setValue(Byte value) {
        this.value = value;
    }

    @Column(name = "was_present", nullable = false)
    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @ManyToOne
    @JoinColumn(name = "period_instance_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public PeriodInstance getPeriodInstance() {
        return periodInstance;
    }

    public void setPeriodInstance(PeriodInstance periodInstance) {
        this.periodInstance = periodInstance;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
