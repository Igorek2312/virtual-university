package ua.km.khnu.virtual.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Year;
import java.util.Collection;

/**
 * @author Igor Rybak
 */
@Entity
@Table(name = "`group`")
public class Group {
    private Integer id;
    private String name;
    private Year yearEntered;
    private int yearOfStudyEntered;
    private Specialty specialty;
    private Collection<Student> students;

    public Group() {
    }

    public Group(Integer id) {
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

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "year_entered", columnDefinition = "YEAR")
    public Year getYearEntered() {
        return yearEntered;
    }

    public void setYearEntered(Year yearEntered) {
        this.yearEntered = yearEntered;
    }

    @Column(name = "year_of_study_entered")
    public int getYearOfStudyEntered() {
        return yearOfStudyEntered;
    }

    public void setYearOfStudyEntered(int yearOfStudyEntered) {
        this.yearOfStudyEntered = yearOfStudyEntered;
    }

    @ManyToOne
    @JoinColumn(name = "specialty_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

     @OneToMany(mappedBy = "group")
     @JsonIgnore
     public Collection<Student> getStudents() {
         return students;
     }

     public void setStudents(Collection<Student> students) {
         this.students = students;
     }
}
