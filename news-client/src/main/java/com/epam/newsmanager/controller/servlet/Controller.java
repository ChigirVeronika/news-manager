package com.epam.newsmanager.controller.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main HTTP servlet control all actions in system.
 */
public class Controller extends HttpServlet {

    /**
     * Handle get http request
     *
     * @throws ServletException if something gone wrong with servlet processing
     * @throws IOException      if something gone wrong with i/o*
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handle post http request
     *
     * @throws ServletException if something gone wrong with servlet processing
     * @throws IOException      if something gone wrong with i/o
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Process post and get http requests.
     * Get command name as request parameter, get corresponding command,
     * try to execute it and get page. Then forward to getting page.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF8");
        String commandName = request.getParameter("command");

    }
}
