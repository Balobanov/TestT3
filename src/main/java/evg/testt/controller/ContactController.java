package evg.testt.controller;

import evg.testt.dto.ContactActivityDTO;
import evg.testt.model.Activity;
import evg.testt.model.ActivityType;
import evg.testt.model.Contact;
import evg.testt.service.ActivityService;
import evg.testt.service.ActivityTypeService;
import evg.testt.service.ContactService;
import evg.testt.util.JspPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by DENNNN on 07.11.2016.
 */
@Controller
public class ContactController {

    @Autowired
    ContactService cs;

    @Autowired
    ActivityTypeService ats;


    @Autowired
    ActivityService as;

    private static String searchName= "";
    private static String searchType = "first";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String redirTocontact()
    {return "redirect:/contacts";}

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView toAddContact(Model model) throws SQLException {
       return new ModelAndView(JspPath.CONTACT);
    }

    @RequestMapping(value = "/contactsAjax", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Contact getAjaxContact(Model model) throws SQLException {
        Contact contact = cs.getById(15);
        return contact;
    }


    @RequestMapping(value = "/contactsAllAjax", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody List<Contact> getAjaxAllContact(Model model) throws SQLException {
        return cs.getAll();
    }

    @RequestMapping(value = "/contactsSaveAjax", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ContactDto saveAjaxAllContact(@RequestBody ContactDto contactDto) throws SQLException {
        return contactDto;
    }
}

class ContactDto
{
    int id;
    String firstName;


    public ContactDto(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public ContactDto() {
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}