package ua.km.khnu.virtual.university.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Igor Rybak
 */
@Entity
@Table(name = "subject_instance")
public class SubjectInstance {
    private Integer id;
    private String controlType;
    private String subjectType;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateBegin;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateEnd;
    private int hours;
    @NotNull
    private Subject subject;
    private Group group;

    public SubjectInstance() {
    }

    public SubjectInstance(Integer id) {
        this.id = id;
    }

    public SubjectInstance(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
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

    @Column(name = "control_type", nullable = false)
    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controllType) {
        this.controlType = controllType;
    }

    @Column(name = "subject_type", nullable = false)
    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    @Column(name = "date_begin", columnDefinition = "DATE", nullable = false)
    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateStart) {
        this.dateBegin = dateStart;
    }

    @Column(name = "date_end", columnDefinition = "DATE", nullable = false)
    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateFinish) {
        this.dateEnd = dateFinish;
    }

    @Column(name = "hours", nullable = false)
    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id", nullable = false)
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
