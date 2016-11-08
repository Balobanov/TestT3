package evg.testt.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

/**
 * Created by clay on 05.10.16.
 */

@Entity(name = "roles")
public class Role extends BaseModel {

    @OneToOne
    @JoinColumn(name = "contact_id")
    private Contact contact;

    private String role;

    public Contact getUser() {
        return contact;
    }

    public void setUser(Contact user) {
        this.contact = user;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
