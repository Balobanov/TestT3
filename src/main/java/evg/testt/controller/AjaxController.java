package evg.testt.controller;
import evg.testt.dto.MoveLead;
import evg.testt.util.JspPath;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by DENNNN on 03.12.2016.
 */
@Controller
public class AjaxController {


    @RequestMapping(value = "/ajax", method = RequestMethod.GET)
    public ModelAndView ajax()
    {
        return new ModelAndView(JspPath.AJAX);
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxCall", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public Ajax ajaxCall()
    {
        return new Ajax(10, 20, "Hello");
    }

    @ResponseBody
    @RequestMapping(value = "/moveLead", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String moveLead(@RequestBody MoveLead moveLead)
    {
        if(moveLead.getFrom() != moveLead.getDestination()) {
            System.out.println("Deleting lead from card: " + moveLead.getFrom());
            System.out.println("Moving lead to card: " + moveLead.getDestination());
            return HttpStatus.ACCEPTED.getReasonPhrase();
        }

        return HttpStatus.BAD_REQUEST.getReasonPhrase();
    }

    private class Ajax
    {
        int a;
        int b;
        String str;

        public Ajax(int a, int b, String str) {
            this.a = a;
            this.b = b;
            this.str = str;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
