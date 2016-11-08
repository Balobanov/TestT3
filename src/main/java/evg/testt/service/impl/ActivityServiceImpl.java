package evg.testt.service.impl;

import evg.testt.dao.ActivityDao;
import evg.testt.model.Activity;
import evg.testt.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Service
@Transactional
public class ActivityServiceImpl extends BaseService<Activity, ActivityDao> implements ActivityService{
}
