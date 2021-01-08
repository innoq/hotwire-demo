package ch.jpr.hotwire.demo.backend.messages;

import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {

    @GetMapping(path = "/backend/messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getMessages() {
        return Collections.singletonList("Hello World");
    }
}
