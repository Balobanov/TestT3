package evg.testt.controller;

import evg.testt.model.Contact;
import evg.testt.oval.SpringOvalValidator;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by DENNNN on 07.11.2016.
 */
@Controller
public class ContactController {

    @Autowired
    SpringOvalValidator validator;

    @Autowired
    ContactService cs;

    @RequestMapping(value = {"/", "", "contacts"}, method = RequestMethod.GET)
    public ModelAndView toAddContact(Model model)
    {
        Contact contact = new Contact();
        List<Contact> contacts = Collections.EMPTY_LIST;

        try {
            contacts = cs.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        model.addAttribute("contact", contact);
        model.addAttribute("contacts", contacts);

        return  new ModelAndView(JspPath.CONTACT);
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
            return new ModelAndView(JspPath.CONTACT);
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

    @RequestMapping(value = "/activities")
    public String awdawds()
    {
        return "activities";
    }
}
