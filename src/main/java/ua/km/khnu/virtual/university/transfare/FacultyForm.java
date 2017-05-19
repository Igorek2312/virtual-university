package ua.km.khnu.virtual.university.transfare;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Igor Rybak
 */
public class FacultyForm {
    @NotBlank
    @Length(min=5,max = 255)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
