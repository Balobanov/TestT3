package evg.testt.service.impl;

import evg.testt.dao.ActivityDao;
import evg.testt.dao.ActivityTypeDao;
import evg.testt.model.ActivityType;
import evg.testt.service.ActivityService;
import evg.testt.service.ActivityTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Service
@Transactional
public class ActivityTypeServiceImpl extends BaseService<ActivityType, ActivityTypeDao> implements ActivityTypeService{
}
