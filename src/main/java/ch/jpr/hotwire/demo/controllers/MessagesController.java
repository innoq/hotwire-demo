package ch.jpr.hotwire.demo.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.ServletWebRequest;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Controller
public class MessagesController {

    private static final String CONTENT_TYPE_TURBO_STREAM="text/html; turbo-stream";
    private List<String> comments;
    private SpringTemplateEngine engine;

    @Autowired
    public MessagesController(SpringTemplateEngine engine) {
        this.comments = new ArrayList<>();
        this.engine = engine;
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

    @PostMapping(path = "/messages/comments/stream")
    public void streamComment(@RequestParam("comment") String comment, ServletWebRequest swr, OutputStream outputStream) throws IOException {

        this.comments.add(comment);
        HttpServletResponse resp = swr.getResponse();
        if (resp != null) {
            resp.setContentType(CONTENT_TYPE_TURBO_STREAM);
            Context context = new Context();
            context.setVariable("comment", comment);
            String response = engine.process("comments-stream", context);
            outputStream.write(response.getBytes(StandardCharsets.UTF_8));
        }

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
