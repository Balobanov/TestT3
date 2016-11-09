package evg.testt.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Entity(name = "activitiesTypes")
public class ActivityType extends BaseModel{

    private String activityTypeName;

    @OneToMany(mappedBy = "activityType", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Activity> activities;

    public String getActivityType() {
        return activityTypeName;
    }

    public void setActivityType(String activityType) {
        this.activityTypeName = activityType;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
