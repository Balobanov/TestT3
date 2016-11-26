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

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Array;
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



    /*
     *  Common data
     */
    private Map<String, Object> init()
    {
        Map<String, Object> attributes = new ModelMap();
        List<ActivityType> activityTypes = Collections.EMPTY_LIST;
        ContactActivityDTO contact = new ContactActivityDTO();

        contact.setContact(new Contact());
        contact.setActivity(new Activity());

        List<Contact> contacts = Collections.EMPTY_LIST;

        try {

          if(searchType.equals("first"))
              contacts = cs.findByFirstName(searchName);
          else
              contacts = cs.findByLastName(searchName);

            activityTypes = ats.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        attributes.put("activityTypes", activityTypes);
        attributes.put("contacts", contacts);
        attributes.put("contact", contact);
        attributes.put("searchName", searchName);

        return attributes;
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveOrUpdate(@Valid @ModelAttribute("contact") ContactActivityDTO contact,
                                     BindingResult bindingResult, Model model, HttpServletRequest request, Integer act_id) {

        String pressedButton = request.getParameter("save");

        // Добавить только контакт
        if (!bindingResult.hasErrors() && pressedButton.equals("Add contact/Edit Contact")){
            try {
                if(contact.getContact().getId() == null || contact.getContact().getId() <= 0)
                    cs.insert(contact.getContact());
                else {
                    Contact c = contact.getContact();
                    c.setActivities(cs.getById(c.getId()).getActivities());
                    cs.update(c);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return toAddContact(model);
        }

        //Добавить контакт и активность
        if (!bindingResult.hasErrors() && pressedButton.equals("Add Activity")){
            Activity activity = contact.getActivity();
            Contact c = contact.getContact();

            ActivityType activityType = null;

            // Если заголовок не установлен вернуться на страницу
            if(activity.getTitle() == null || activity.getTitle().length() < 3){
                bindingResult.rejectValue("activity.title", "activity.title", "Title must be more than 3 character");
                return new ModelAndView(JspPath.CONTACT).addAllObjects(init()).addObject("contact", contact);
            }

            try {
                activityType = ats.getById(act_id);
                if(contact.getContact().getId() == null || contact.getContact().getId() <= 0)
                    cs.insert(c);
                else
                    c = cs.getById(c.getId());

                    as.insert(activity);
                    activity.setContact(c);
                    activity.setActivityType(activityType);
                    as.update(activity);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return toAddContact(model);
        }


        return new ModelAndView(JspPath.CONTACT).addAllObjects(init()).addObject("contact", contact);
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
        ContactActivityDTO contactActivityDTO = new ContactActivityDTO();

        try {
            c = cs.getById(id);
            contactActivityDTO.setContact(c);
            contactActivityDTO.setActivity(new Activity());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  new ModelAndView(JspPath.CONTACT).addAllObjects(init()).addObject("contact", contactActivityDTO);
    }

    @RequestMapping(value = "/toActivityPage", method = RequestMethod.POST)
    public String toActivities()
    {
        return "redirect:/activities";
    }


    @RequestMapping(value = "/saveContactandActivity", method = RequestMethod.POST)
    public ModelAndView saveContactandActivity(@Valid @ModelAttribute("contact") ContactActivityDTO contact,
                                               BindingResult bindingResult,
                                               Model model,
                                               Integer act_id) {


        return new ModelAndView(JspPath.CONTACT).addAllObjects(init()).addObject("contact", contact);
    }

    @RequestMapping(value = "/searchContact", method = RequestMethod.POST)
    public ModelAndView search(@RequestParam(required = true) String name, HttpServletRequest request)
    {
        searchName = name;
        searchType = request.getParameter("type");
        return new ModelAndView(JspPath.CONTACT).addAllObjects(init());
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public ModelAndView test(HttpServletRequest req,
                             @ModelAttribute("wrapper") Wrapper wrapper,
                             BindingResult bindResult, ModelMap model)
    {

        return new ModelAndView(JspPath.CONTACT);
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET)
    public ModelAndView toAddContact(Model model)
    {
        Wrapper w = new Wrapper();

        List<TestEntity> list = new LinkedList<>();
        list.add(new TestEntity(11, "Vasyan"));
        list.add(new TestEntity(12, "Petyan"));

        w.setTestEntityList(list);

        return  new ModelAndView(JspPath.CONTACT).addObject("wrapper", w);
    }
}

//<form id="wrapper" action="/test" method="post">
//
//<label for="testEntityList0.name">College Name</label>
//<input id="testEntityList0.name" name="testEntityList[0].name" type="text" value="vasya"/>
//<input id="testEntityList0.id" name="testEntityList[0].id" type="text" value="1"/>
//
//<label for="testEntityList1.name">College Name</label>
//<input id="testEntityList1.name" name="testEntityList[1].name" type="text" value="petya"/>
//<input id="testEntityList1.id" name="testEntityList[1].id" type="text" value="2"/>
//
//<input type="submit">
//</form>


//<form id="wrapper" action="/test" method="post">
//
//<label for="testEntityList0.name">College Name</label>
//<input id="testEntityList0.name" name="testEntityList[0].name" type="text" value="Vasyan"/>
//<input id="testEntityList0.id" name="testEntityList[0].id" type="text" value="11"/>
//
//<input id="testEntityList0.mailsList0.mail" name="testEntityList[0].mailsList[0].mail" type="text" value="dddddd"/>
//<input id="testEntityList0.mailsList1.mail" name="testEntityList[0].mailsList[1].mail" type="text" value="dddddd"/>
//
//
//
//<label for="testEntityList1.name">College Name</label>
//<input id="testEntityList1.name" name="testEntityList[1].name" type="text" value="Petyan"/>
//<input id="testEntityList1.id" name="testEntityList[1].id" type="text" value="12"/>
//
//<input id="testEntityList1.mailsList0.mail" name="testEntityList[1].mailsList[0].mail" type="text" value="dddddd"/>
//<input id="testEntityList1.mailsList1.mail" name="testEntityList[1].mailsList[1].mail" type="text" value="dddddd"/>
//
//<input id="flag1" name="flag" type="checkbox" value="true"/><input type="hidden" name="_flag" value="on"/>
//
//<input type="submit">
//</form>

