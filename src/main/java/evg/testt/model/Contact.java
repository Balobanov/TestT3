package evg.testt.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 9/10/15.
 */
@Entity(name = "contacts")
public class Contact extends BaseModel {

    @NotNull(message = "Must not be empty.")
    @Size(min = 3, message = "Name must be more than 3 character")
    private String firstName;

    @NotNull(message = "Must not be empty.")
    @Size(min = 3, message = "First name must be more than 3 character")
    private String lastName;

    @NotNull(message = "Must not be empty.")
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]" +
            "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Invalid email address.")
    private String email;

    private String phone;
    private String address1;
    private String address2;
    private String city;
    private String postCode;

    @OneToMany(mappedBy = "contact", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Activity> activities;

    public Contact(){
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities.clear();
        if (activities != null) {
            this.activities.addAll(activities);
        }
    }
}
