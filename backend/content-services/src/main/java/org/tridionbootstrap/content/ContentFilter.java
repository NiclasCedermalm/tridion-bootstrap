package org.tridionbootstrap.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Content Filter
 */
public class ContentFilter implements Filter {

    Logger log = LoggerFactory.getLogger(ContentFilter.class);

    // TODO: Add a constructor for configuring content path

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        log.info("REQUEST URI: " + httpRequest.getRequestURI());
        File contentFile = new File("../../../content" + httpRequest.getRequestURI());
        if ( contentFile.exists() ) {
            FileInputStream is = new FileInputStream(contentFile);
            int bytesRead;
            byte[] bytes = new byte[4096];
            while ( true ) {
                bytesRead = is.read(bytes);
                servletResponse.getOutputStream().write(bytes, 0, bytesRead);
                if ( bytesRead < 4096 ) break;
            }
            servletResponse.getOutputStream().close();
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
