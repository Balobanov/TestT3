package evg.testt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by DENNNN on 08.11.2016.
 */
@Controller
public class ActivityController {
    @RequestMapping(value = "/activities")
    public String renderActivitypage(Model model)
    {
        return "activities";
    }
}
