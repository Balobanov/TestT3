package evg.testt.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Entity(name = "activities")
public class Activity extends BaseModel{

    private String title;
    private String notes;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contacts_id")
    private Contact contact;

    public Activity() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
