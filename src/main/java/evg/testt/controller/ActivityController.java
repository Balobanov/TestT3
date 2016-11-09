package evg.testt.controller;

import evg.testt.dto.ActivityDto;
import evg.testt.model.Activity;
import evg.testt.model.ActivityType;
import evg.testt.model.Contact;
import evg.testt.oval.SpringOvalValidator;
import evg.testt.service.ActivityService;
import evg.testt.service.ActivityTypeService;
import evg.testt.service.ContactService;
import evg.testt.util.JspPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    SpringOvalValidator validator;

    @Autowired
    ContactService cs;

    @Autowired
    ActivityTypeService ats;

    @Autowired
    ActivityService as;

    @RequestMapping(value = "/activities")
    public ModelAndView renderActivitypage(Model model)
    {
        List<Contact> contacts = Collections.EMPTY_LIST;
        List<ActivityType> activityTypes = Collections.EMPTY_LIST;
        ActivityDto activity = new ActivityDto();

        try {
            contacts = cs.getAll();
            activityTypes = ats.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ModelAndView modelV = new ModelAndView(JspPath.ACTIVITIES);

        modelV.addObject("contacts", contacts);

        modelV.addObject("activityTypes", activityTypes);

        modelV.addObject("activity", activity);

        return modelV;
    }

    @RequestMapping(value = "/saveAvtivity", method = RequestMethod.POST)
    public ModelAndView saveActivity(@ModelAttribute("activity") @Validated ActivityDto activity,
                                     BindingResult bindingResult,
                                     Model model,
                                     Integer c_id,
                                     Integer act_id)
    {
        Activity a = new Activity();
        SimpleDateFormat sdtf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar;

        try {
            date = sdtf.parse(activity.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar = new GregorianCalendar();
        calendar.setTime(date);

        validator.validate(activity, bindingResult);
        if (!bindingResult.hasErrors()) {

            try {
                Contact c = cs.getById(c_id);
                ActivityType at = ats.getById(act_id);

                a.setDate(calendar);
                a.setTitle(activity.getTitle());
                a.setNotes(activity.getNotes());

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            return new ModelAndView(JspPath.ACTIVITIES);
        }
        return null;
    }
}
