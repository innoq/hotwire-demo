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

    private static final String CONTENT_TYPE_TURBO_STREAM="text/vnd.turbo-stream.html";
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
    public String newComment(@RequestParam("comment") String comment) {
        this.comments.add(comment);
        return "redirect:/page1";
    }

    @PostMapping(path = "/messages/comments/remove")
    public String removeComment(Model model) {
        if (!this.comments.isEmpty()) {
            this.comments.remove(0);
        }
        model.addAttribute("comments", this.comments);
        return "comments";
    }

    @PostMapping(path = "/messages/comments/stream", produces = CONTENT_TYPE_TURBO_STREAM)
    public String streamComment(@RequestParam("comment") String comment, Model model) {
        this.comments.add(comment);
        model.addAttribute("comment", comment);
        return "comments-stream";
    }

    @PostMapping(path = "/messages/links")
    public String breakoutLinks() {
        return "breakout-links";
    }

    @PostMapping(path = "/messages/links-inside")
    public String noBreakoutLinks() {
        return "no-breakout-links";
    }
}
