package evg.testt.controller;

import evg.testt.model.Activity;
import evg.testt.model.ActivityType;
import evg.testt.model.Contact;
import evg.testt.oval.SpringOvalValidator;
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

import java.sql.SQLException;
import java.util.*;

/**
 * Created by DENNNN on 07.11.2016.
 */
@Controller
public class ContactController {

    @Autowired
    SpringOvalValidator validator;

    @Autowired
    ContactService cs;

    @Autowired
    ActivityTypeService ats;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String redirTocontact()
    {return "redirect:/contacts";}

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView toAddContact(Model model)
    {
        return  new ModelAndView(JspPath.CONTACT).addAllObjects(init());
    }

    private Map<String, Object> init()
    {
        Map<String, Object> attributes = new ModelMap();

        Contact contact = new Contact();
        List<Contact> contacts = Collections.EMPTY_LIST;

        try {
            contacts = cs.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        attributes.put("contacts", contacts);
        attributes.put("contact", contact);

        return attributes;
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@ModelAttribute("contact") @Validated Contact contact,
                                  BindingResult bindingResult, Model model) {
        validator.validate(contact, bindingResult);

        if (!bindingResult.hasErrors()) {
            try {
                if(contact.getId() == null || contact.getId() <= 0)
                    cs.insert(contact);
                else
                    cs.update(contact);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return toAddContact(model);
        } else {
            return new ModelAndView(JspPath.CONTACT).addAllObjects(init()).addObject("contact", contact);
        }
    }

    @RequestMapping(value = "/deleteContact", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam(required = true) int id, Model model) {

        Contact c = null;
        try {
           c = cs.getById(id);
            if(c != null)
                cs.delete(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toAddContact(model);
    }


    @RequestMapping(value = "/editContact", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(required = true) int id, Model model) {

        Contact c = new Contact();
        List<Contact> contacts = Collections.EMPTY_LIST;

        try {
            c = cs.getById(id);
            contacts = cs.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("contact", c);
        model.addAttribute("contacts", contacts);

        return  new ModelAndView(JspPath.CONTACT);
    }

    @RequestMapping(value = "/toActivityPage", method = RequestMethod.POST)
    public String toActivities()
    {
        return "redirect:/activities";
    }


}

//    ActivityType a = new ActivityType();
//        a.setActivityType("Mail");
//                try {
//                ats.insert(a);
//
//                a = new ActivityType();
//                a.setActivityType("Skype");
//                ats.insert(a);
//
//                a = new ActivityType();
//                a.setActivityType("Phone");
//                ats.insert(a);
//
//                } catch (SQLException e) {
//                e.printStackTrace();
//                }