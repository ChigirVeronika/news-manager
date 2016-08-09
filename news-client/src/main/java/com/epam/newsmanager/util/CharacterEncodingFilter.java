package com.epam.newsmanager.util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Implements the following methods from the interface {@link Filter}: init, destroy, doFilter
 */
@WebFilter(urlPatterns = {"/*"}, initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8")
})
public class CharacterEncodingFilter implements Filter {
    private static final String PARAM_ENCODING_NAME = "encoding";
    private String encoding;

    /**
     * Called by the web container to indicate to a filter that it is being placed into service. The servlet container
     * calls the init method exactly once after instantiating the filter. The init method must complete successfully
     * before the filter is asked to do any filtering work.
     * Extracts encoding value from {@link FilterConfig} parameters.
     *
     * @param filterConfig a filter configuration object used by a servlet container to pass information to a filter
     *                     during initialization.
     * @throws ServletException if an exception occurs that interrupts the filter's normal operation
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(PARAM_ENCODING_NAME);
    }

    /**
     * The doFilter method of the Filter is called by the container each time a request/response pair is passed through
     * the chain due to a client request for a resource at the end of the chain. The FilterChain passed in to this method
     * allows the Filter to pass on the request and response to the next entity in the chain.
     * Sets {@link ServletRequest} encoding to UTF-8.
     *
     * @param servletRequest an object to provide client request information to a servlet
     * @param servletResponse an object to assist a servlet in sending a response to the client
     * @param filterChain an object provided by the servlet container to the developer giving a view into the invocation
     *                    chain of a filtered request for a resource
     * @throws IOException if an input/output exception occurs that interrupts the filter's normal operation
     * @throws ServletException if an exception occurs that interrupts the filter's normal operation
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String requestEncoding = servletRequest.getCharacterEncoding();
        if (encoding != null && !encoding.equalsIgnoreCase(requestEncoding)) {
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Called by the web container to indicate to a filter that it is being taken out of service. This method is only
     * called once all threads within the filter's doFilter method have exited or after a timeout period has passed.
     * After the web container calls this method, it will not call the doFilter method again on this instance of the
     * filter.
     * Sets encoding object to null.
     */
    @Override
    public void destroy() {
        encoding = null;
    }
}
