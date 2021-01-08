package ch.jpr.hotwire.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessagesController {

    private List<String> comments;

    public MessagesController() {
        this.comments = new ArrayList<>();
    }

    public List<String> getComments() {
        return this.comments;
    }

    @GetMapping(path = "/messages/comments")
    public String comments(Model model) throws InterruptedException {
        Thread.sleep(1000);
        model.addAttribute("comments", this.comments);
        return "comments";
    }

    @PostMapping(path = "/messages/comments")
    public String newComment(@RequestParam("comment") String comment, Model model) {

        this.comments.add(comment);
        model.addAttribute("comments", this.comments);
        return "comments";
    }

    @PostMapping(path = "/messages/comments/poo")
    public String newCommentAnswerWithRedirect(@RequestParam("comment") String comment) {

        this.comments.add(comment);
        return "redirect:/page1";
    }

}
