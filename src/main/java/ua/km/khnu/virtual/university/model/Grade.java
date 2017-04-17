package ua.km.khnu.virtual.university.model;

import javax.persistence.*;

/**
 * @author Igor Rybak
 */
@Entity
public class Grade {
    private Integer id;
    private byte value;
    private PeriodInstance periodInstance;

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
    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "period_instance_id", referencedColumnName = "id", nullable = false)
    public PeriodInstance getPeriodInstance() {
        return periodInstance;
    }

    public void setPeriodInstance(PeriodInstance periodInstance) {
        this.periodInstance = periodInstance;
    }
}
