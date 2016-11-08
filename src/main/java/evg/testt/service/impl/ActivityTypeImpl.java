package evg.testt.service.impl;

import evg.testt.dao.ActivityDao;
import evg.testt.dao.ActivityTypeDao;
import evg.testt.model.ActivityType;
import evg.testt.service.ActivityService;
import evg.testt.service.ActivityTypeService;
import org.springframework.stereotype.Service;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Service
public class ActivityTypeImpl extends BaseService<ActivityType, ActivityTypeDao> implements ActivityTypeService{
}
