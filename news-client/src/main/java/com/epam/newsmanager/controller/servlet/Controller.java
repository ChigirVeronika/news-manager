package com.epam.newsmanager.controller.servlet;

import com.epam.newsmanager.controller.command.Command;
import com.epam.newsmanager.controller.command.CommandHelper;
import com.epam.newsmanager.controller.exception.CommandException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main HTTP servlet control all actions in system.
 */
//@WebServlet("*.jsp")
public class Controller extends HttpServlet {

    public Controller() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

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

        Command command = CommandHelper.getInstance().getCommand(commandName);
        String page;

        try {
            page = command.execute(request, response);
        } catch (CommandException e) {
            page = "error-page.jsp";
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);

        if (requestDispatcher != null) {
            requestDispatcher.forward(request, response);
        }

    }
}
