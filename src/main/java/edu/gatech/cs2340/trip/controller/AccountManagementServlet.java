package edu.gatech.cs2340.trip.controller;

/**
 * Created by dheavern on 6/3/14.
 */
import edu.gatech.cs2340.trip.model.*;

import java.io.IOException;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.LoginContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns =
        {"/login",
         "/register"
        })
public class AccountManagementServlet extends HttpServlet {
    private AccountDAO acd = new SqlliteAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
        throws IOException, ServletException {
        if (request.getRequestURI().equals("/trip/login")) {
            handleLogin(request, response);
        } else if(request.getRequestURI().equals("/trip/register")) {
            handleRegistration(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        return;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return;
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        String name = (String) req.getAttribute("Username");
        String password = (String) req.getAttribute("Password");
        Account userAccount;
        try {
            userAccount = LoginManager.attemptLogin(name, password);
            req.getSession().setAttribute("account", userAccount);
            req.setAttribute("error", "You are now logged in ");
        } catch (AccountNotFoundException e) {
            req.setAttribute("error", "This account does not exist");
        } catch (AccountException e) {
            req.setAttribute("error", "The credentials supplied do not match");
        }

        RequestDispatcher reqDispatcher = getServletConfig().getServletContext().getRequestDispatcher("/main.jsp");
        reqDispatcher.forward(req, resp);
    }

    private void handleRegistration(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try {
            RegistrationManager.registerAccount("test", "test", "test", "test");
        } catch (Exception e) {

        }
    }
}
