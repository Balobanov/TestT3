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
        return new ModelAndView(JspPath.ACTIVITIES);
    }
}
