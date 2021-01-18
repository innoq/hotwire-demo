package ch.jpr.hotwire.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
public class TurboStreamsViewResolverConfiguration {
    
    @Bean
    public ThymeleafViewResolver turboStreamViewResolver(SpringTemplateEngine engine) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setContentType("text/vnd.turbo-stream.html");
        resolver.setOrder(2);
        resolver.setTemplateEngine(engine);
        resolver.setViewNames(new String[] {"comments-stream"});
        return resolver;
    }
}
