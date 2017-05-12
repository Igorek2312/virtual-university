package ua.km.khnu.virtual.university.model;

import javax.persistence.*;

/**
 * @author Igor Rybak
 */
@Entity
@SequenceGenerator(name = "seq", initialValue = 1, allocationSize = 100)
public class Faculty {
    private Integer id;
    private String name;

    public Faculty() {
    }

    public Faculty(Integer id) {
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

    @Column(name = "name",nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
