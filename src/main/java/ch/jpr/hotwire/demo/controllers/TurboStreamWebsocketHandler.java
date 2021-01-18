package ch.jpr.hotwire.demo.controllers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class TurboStreamWebsocketHandler extends TextWebSocketHandler {

    private WebSocketSession session;

    @Autowired
    private SpringTemplateEngine templateEngine;

    private Logger logger = LoggerFactory.getLogger(TurboStreamWebsocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        this.session = session;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        this.session = null;
    }

    private void sendUpdate(String text) throws IOException {
        if (this.session != null) {
            WebSocketMessage<String> message = new TextMessage(text);
            this.session.sendMessage(message);
        }
    }

    @Scheduled(fixedRate = 2000)
    private void scheduleTaskWithFixedRate() throws IOException {
        Context context = new Context();
        context.setVariable("time", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        String content = this.templateEngine.process("socket-update", context);
        
        this.sendUpdate(content);
    }

}
