package com.rudenkoinc.crm.app.model;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(
                name = User.FIND_BY_EMAIL,
                query = "select u from User u where email = :email"
        )
})
public class User extends AbstractEntity {
    public static final String FIND_BY_EMAIL = "user.findByEmail";

    private String firstName;
    private String secondName;
    private String passwordDigest;
    private String email;

    public User() {

    }

    public User(String firstName, String secondName, String passwordDigest, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.passwordDigest = passwordDigest;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
