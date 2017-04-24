package ua.km.khnu.virtual.university.model;

import javax.persistence.*;

/**
 * @author Igor Rybak
 */
@Entity
public class Teacher {
    private Integer id;
    private Account account;

    public Teacher() {
    }

    public Teacher(Integer id) {
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
