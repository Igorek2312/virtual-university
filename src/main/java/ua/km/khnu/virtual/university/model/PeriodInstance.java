package ua.km.khnu.virtual.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Igor Rybak
 */
@Entity
@Table(name = "period_instance")
public class PeriodInstance {
    private Integer id;
    private String type;
    private LocalDate date;
    private Period period;

    public PeriodInstance() {
    }

    public PeriodInstance(Integer id) {
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

    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "date", columnDefinition = "DATE")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "period_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
