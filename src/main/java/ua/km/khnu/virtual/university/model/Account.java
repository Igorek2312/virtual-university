package ua.km.khnu.virtual.university.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import ua.km.khnu.virtual.university.service.validation.NullOrNotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Igor Rybak
 */
@Entity
public class Account {
    private Integer id;
    @NotBlank
    @Length(min = 1,max = 255)
    private String firstName;
    @NotBlank
    @Length(min = 1,max = 255)
    private String lastName;
    @NotBlank
    @Length(min = 1,max = 255)
    private String middleName;
    @NullOrNotBlank
    @Length(min = 5,max = 20)
    private String username;
    @NullOrNotBlank
    @Length(min = 5,max = 20)
    private String password;
    private Set<Role> roles = new HashSet<>();
    @NotBlank
    @Length(min = 1,max = 10)
    private String documentNumber;
    private boolean enabled;

    public Account() {
    }

    public Account(Account account) {
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.enabled = account.isEnabled();
        this.firstName = account.firstName;
        this.lastName = account.lastName;
        this.roles = account.getRoles();
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

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "middle_name")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> role) {
        this.roles = role;
    }

    @Column(name = "document_number", unique = true)
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


}
