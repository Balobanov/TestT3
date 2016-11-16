package evg.testt.controller;

import evg.testt.dto.ActivityDto;
import evg.testt.model.Activity;
import evg.testt.model.ActivityType;
import evg.testt.model.Contact;
import evg.testt.service.ActivityService;
import evg.testt.service.ActivityTypeService;
import evg.testt.service.ContactService;
import evg.testt.util.JspPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Controller
public class ActivityController {

    @Autowired
    ContactService cs;

    @Autowired
    ActivityTypeService ats;

    @Autowired
    ActivityService as;

    private static String searchSubject = "";

    @RequestMapping(value = "/activities", method = RequestMethod.GET)
    public ModelAndView renderActivitypage(Model model)
    {
        return new ModelAndView(JspPath.ACTIVITIES).addAllObjects(init());
    }

    private Map<String, Object> init()
    {
        Map<String, Object> attributes = new ModelMap();

        List<Activity> activities = Collections.EMPTY_LIST;
        List<Contact> contacts = Collections.EMPTY_LIST;
        List<ActivityType> activityTypes = Collections.EMPTY_LIST;
        Activity activity = new Activity();

        try {
            contacts = cs.getAll();
            activities = as.findBySubject(searchSubject);
            activityTypes = ats.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        attributes.put("contacts", contacts);
        attributes.put("activities", activities);
        attributes.put("activityTypes", activityTypes);
        attributes.put("activity", activity);
        attributes.put("searchSubject", searchSubject);

        return attributes;
    }

    @RequestMapping(value = "/saveAvtivity", method = RequestMethod.POST)
    public ModelAndView saveOrUpdateActivity(@Valid @ModelAttribute("activity") Activity activity,
                                     BindingResult bindingResult,
                                     Model model,
                                     Integer c_id,
                                     Integer act_id)
    {
        if (!bindingResult.hasErrors()) {

            try {
                Contact c = cs.getById(c_id);
                ActivityType at = ats.getById(act_id);

                if(activity.getId() == null || activity.getId() <= 0) {
                    as.insert(activity);
                }
                    activity.setContact(c);
                    activity.setActivityType(at);
                    as.update(activity);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return renderActivitypage(model);
        } else {
            return new ModelAndView(JspPath.ACTIVITIES).addAllObjects(init()).addObject("activity", activity);
        }

    }

    @RequestMapping(value = "/editActivity", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required = true) int id, Model model) {

        Activity activity = new Activity();

        if(id > 0) {
            try {
                activity = as.getById(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new ModelAndView(JspPath.ACTIVITIES).addAllObjects(init()).addObject("activity", activity);
        }
        else
            return new ModelAndView(JspPath.ACTIVITIES).addAllObjects(init());
    }

    @RequestMapping(value = "/deleteaAtivity", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(required = true) int id, Model model) {
        Activity activity = null;
        try
        {
            if(id > 0) {
                activity = as.getById(id);
                activity.setContact(null);
                as.update(activity);
                as.delete(activity);
            }
        }
        catch (SQLException e)
        {e.printStackTrace();}

        return renderActivitypage(model);
    }

    @RequestMapping(value = "/toContactPage", method = RequestMethod.POST)
    public String toActivities()
    {
        return "redirect:/contacts";
    }

    @RequestMapping(value = "/searchActivity", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(required = true) String name, HttpServletRequest request)
    {
        searchSubject = name;
        return new ModelAndView(JspPath.ACTIVITIES).addAllObjects(init());
    }
}
