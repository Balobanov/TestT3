package evg.testt.service.impl;

import evg.testt.dao.ActivityDao;
import evg.testt.model.Activity;
import evg.testt.model.Contact;
import evg.testt.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Service
@Transactional
public class ActivityServiceImpl extends BaseService<Activity, ActivityDao> implements ActivityService{
    @Override
    public List<Activity> findBySubject(String subject) {
        Query q = em.createQuery("from activities a where a.title like :subject");
        q.setParameter("subject", "%" + subject + "%");
        return q.getResultList();
    }
}
