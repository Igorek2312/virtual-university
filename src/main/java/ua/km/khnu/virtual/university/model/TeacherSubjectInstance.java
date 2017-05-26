package ua.km.khnu.virtual.university.model;

import javax.persistence.*;
import java.text.MessageFormat;

/**
 * @author Igor Rybak
 */
@Entity
@Table(name = "teacher_subject_instance")
public class TeacherSubjectInstance {
    private int id;
    private String periodType;
    private Teacher teacher;
    private SubjectInstance subjectInstance;

    public TeacherSubjectInstance() {
    }

    public TeacherSubjectInstance(int id) {
        this.id = id;
    }

    @Transient
    public String getDetailName() {
        String subjectName = subjectInstance.getSubject().getName();
        return MessageFormat.format("{0} ({1})", subjectName, periodType);
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "period_type", nullable = false)
    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
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
