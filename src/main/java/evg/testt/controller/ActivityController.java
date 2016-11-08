package evg.testt.controller;

import evg.testt.model.ActivityType;
import evg.testt.model.Contact;
import evg.testt.service.ActivityTypeService;
import evg.testt.service.ContactService;
import evg.testt.util.JspPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Controller
public class ActivityController {

    @Autowired
    ContactService cs;

    @Autowired
    ActivityTypeService atc;

    @RequestMapping(value = "/activities")
    public ModelAndView renderActivitypage(Model model)
    {
        List<Contact> contacts = Collections.emptyList();
        List<ActivityType> activityTypes = Collections.EMPTY_LIST;

        try {
            contacts = cs.getAll();
            activityTypes = atc.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ModelAndView(JspPath.ACTIVITIES);
    }
}
