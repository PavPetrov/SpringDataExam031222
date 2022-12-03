package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Mechanic extends BaseEntity {

    @Column(name = "firs_name", unique = true, nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = true)
    private String phone;

    public Mechanic() {
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
