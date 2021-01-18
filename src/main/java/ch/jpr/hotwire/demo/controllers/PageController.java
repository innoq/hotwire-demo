package ch.jpr.hotwire.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    MessagesController messagesController;

    @GetMapping(path = "/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping(path = "/page1")
    public String page1(Model model) {
        List<String> comments = messagesController.getComments();
        model.addAttribute("comments", comments);
        return "page1";
    }

    @GetMapping(path = "/page2")
    public String page2(Model model) {
        List<String> comments = messagesController.getComments();
        model.addAttribute("comments", comments);
        return "page2";
    }

    @GetMapping(path = "/page3")
    public String page3() {
        return "page3";
    }
    
    @GetMapping(path = "/page4")
    public String page4() {
        return "page4";
    }
    
    @GetMapping(path = "/page5")
    public String page5() {
        return "page5";
    }
    
    @GetMapping(path = "/page6")
    public String page6() {
        return "page6";
    }
}
