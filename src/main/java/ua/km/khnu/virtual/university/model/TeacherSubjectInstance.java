package ua.km.khnu.virtual.university.model;

import javax.persistence.*;

/**
 * @author igorek2312
 */
@Entity
@Table(name = "teacher_subject_instance")
public class TeacherSubjectInstance {
    private int id;
    private String periodType;
    private Teacher teacher;
    private SubjectInstance subjectInstance;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "period_type")
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
