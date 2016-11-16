package evg.testt.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by DENNNN on 08.11.2016.
 */
@Entity(name = "activities")
public class Activity extends BaseModel{

    @NotNull(message = "Must not be empty.")
    @Size(min = 3, message = "Name must be more than 3 character")
    private String title;


    private String notes;

    @Temporal(TemporalType.DATE)
    private Calendar date = Calendar.getInstance();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contacts_id")
    private Contact contact;

    @ManyToOne(fetch = FetchType.EAGER)
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

    public String getDate() {
        SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = date.getTime();

        String stringDate;

        stringDate = sdtf.format(d);

        return stringDate;
    }

    public void setDate(String dateS) {
        SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = sdtf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date.setTime(d);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
