package ch.jpr.hotwire.demo.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Controller
public class TurboStreamSSEController {

    private SseEmitter emitter;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @GetMapping(path="/turbo-sse", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handle() {
        this.emitter = new SseEmitter();
        this.emitter.onCompletion(() ->  this.emitter = null);
        return emitter;
    }

    @Scheduled(fixedRate = 2000)
    private void scheduleTaskWithFixedRate() throws IOException {
        Context context = new Context();
        context.setVariable("time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        String content = this.templateEngine.process("socket-update", context);
        content = content.replace("\n", "");
        if (this.emitter != null) {
            this.emitter.send(content);
        }
    }

}
