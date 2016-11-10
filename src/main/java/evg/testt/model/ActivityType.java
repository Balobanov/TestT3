package evg.testt.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Entity(name = "activitiesTypes")
public class ActivityType extends BaseModel{

    private String activityTypeName;

    @OneToMany(mappedBy = "activityType", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Activity> activities = new ArrayList<>();

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
        this.activities.clear();
        if (activities != null) {
            this.activities.addAll(activities);
        }
    }

    @Override
    public String toString() {
        return activityTypeName;
    }
}
