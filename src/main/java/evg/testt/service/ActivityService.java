package evg.testt.service;

import evg.testt.model.Activity;
import evg.testt.model.Contact;

import java.util.List;

/**
 * Created by DENNNN on 08.11.2016.
 */
public interface ActivityService extends Service<Activity>{
    List<Activity> findBySubject(String subject);
}
