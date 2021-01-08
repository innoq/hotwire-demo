package ch.jpr.hotwire.demo.configuration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;

@Component
public class TurboStreamMimeTypeFilter implements Filter {

    private static final String CONTENT_TYPE_TURBO_STREAM="text/html; turbo-stream";

    @Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        response.setContentType(CONTENT_TYPE_TURBO_STREAM);
    }
}
