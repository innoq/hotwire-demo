package ch.jpr.hotwire.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import ch.jpr.hotwire.demo.controllers.TurboStreamWebsocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(turboStreamHandler(), "/stream-updates");
    }

    @Bean
    public WebSocketHandler turboStreamHandler() {
        return new TurboStreamWebsocketHandler();
    }
}
