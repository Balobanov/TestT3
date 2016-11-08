package evg.testt.model;

import javax.persistence.Entity;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Entity(name = "activitiesType")
public class ActivityType extends BaseModel{

    private String activityType;

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }
}
