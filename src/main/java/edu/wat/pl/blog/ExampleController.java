package edu.wat.pl.blog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExampleController {

    @RequestMapping("/ex")
    public String exmaple(ModelAndView modelAndView){
        modelAndView.addObject("ol","Hello mate");
        return "ex";
    }
}
