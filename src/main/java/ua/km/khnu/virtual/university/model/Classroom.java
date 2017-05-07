package ua.km.khnu.virtual.university.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Igor Rybak
 */
@Entity
public class Classroom {
    private Integer id;
    private String name;

    public Classroom() {
    }

    public Classroom(Integer id) {
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
}
