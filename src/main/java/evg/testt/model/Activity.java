package evg.testt.model;

import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotNull;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Entity(name = "activities")
public class Activity extends BaseModel{

    @NotNull
    @Length(min = 1)
    private String title;
    private String notes;

    @Temporal(TemporalType.DATE)
    private Calendar date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts_id")
    private Contact contact;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "activitiesTypes_id")
    private ActivityType activityType;

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
