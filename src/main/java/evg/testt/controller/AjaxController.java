package evg.testt.controller;
import evg.testt.util.JspPath;
import org.springframework.http.HttpStatus;
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
    @RequestMapping(value = "/moveLead", method = RequestMethod.GET, consumes = "application/json", produces = "application/json")
    public HttpStatus ajaxCall(@RequestBody MoveLead moveLead)
    {
        System.out.println("Deleting lead from card: " + moveLead.from);
        System.out.println("Moving lead to card: " + moveLead.destination);
        return HttpStatus.ACCEPTED;
    }

    private class MoveLead
    {
        int destination;
        int from;

        public MoveLead() {
        }

        public int getDestination() {
            return destination;
        }

        public void setDestination(int destination) {
            this.destination = destination;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }
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
