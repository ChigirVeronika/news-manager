package com.epam.newsmanager.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Filter charset.
 */
@WebFilter(urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8", description = "Encoding Param")})
public class CharsetUtil implements Filter {

    private String encoding;
    private ServletContext context;

    private static final String CHARACTER = "characterEncoding";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter(CHARACTER);
        context = filterConfig.getServletContext();
    }

    /**
     * Filter charset
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain chain of filters
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        servletResponse.setCharacterEncoding(encoding);

        context.log("Charset was set successfully.");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
