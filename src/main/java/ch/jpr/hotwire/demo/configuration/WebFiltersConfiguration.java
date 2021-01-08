package ch.jpr.hotwire.demo.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebFiltersConfiguration {
  @Bean
  public FilterRegistrationBean<TurboStreamMimeTypeFilter> mimeTypeFilter(){
      FilterRegistrationBean<TurboStreamMimeTypeFilter> registrationBean 
        = new FilterRegistrationBean<>();
          
      registrationBean.setFilter(new TurboStreamMimeTypeFilter());
      registrationBean.addUrlPatterns("/messages/comments/stream");
          
      return registrationBean;    
  }
}
