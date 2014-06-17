package edu.gatech.cs2340.trip.controller;

/**
 * Created by dheavern on 6/3/14.
 */
import edu.gatech.cs2340.trip.model.RegistrationManager;
import edu.gatech.cs2340.trip.model.LoginManager;
import edu.gatech.cs2340.trip.model.UpdateAccountManger;
import edu.gatech.cs2340.trip.model.Account;


import java.io.IOException;
import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.CredentialException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns =
        {"/login",
         "/register",
         "/logout",
         "/update"
        })
public class AccountManagementServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
        throws IOException, ServletException {

        if (request.getRequestURI().equals("/trip/login")) {
            handleLogin(request, response);
        } else if (request.getRequestURI().equals("/trip/register")) {
            handleRegistration(request, response);
        } else if (request.getRequestURI().equals("/trip/update")) {
            handleUpdates(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher requestDispatcher = null;
        if (request.getRequestURI().equals("/trip/login")) {
            if (request.getSession().getAttribute("account") != null) {
                response.sendRedirect("/trip/main.jsp");
                return;
            } else {
                requestDispatcher =
                        getServletContext().getRequestDispatcher("/login.jsp");
            }
        } else if (request.getRequestURI().equals("/trip/register")) {
            requestDispatcher =
                    getServletContext().getRequestDispatcher("/register.jsp");
        } else if (request.getRequestURI().equals("/trip/update")) {
            requestDispatcher =
                    getServletContext().getRequestDispatcher("/update.jsp");
        } else if (request.getRequestURI().equals("/trip/logout")) {
            request.getSession().setAttribute("account", null);
            response.sendRedirect("/trip/login");
            return;
        }
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    }

    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
        throws IOException, ServletException {
        String name = req.getParameter("Username");
        String password = req.getParameter("Password");
        Account userAccount = null;
        try {
            userAccount = LoginManager.attemptLogin(name, password);
        } catch (AccountNotFoundException e) {
            req.setAttribute("error", e.getMessage());
        } catch (AccountException e) {
            req.setAttribute("error", e.getMessage());
        }
        if (userAccount != null) {
            req.getSession().setAttribute("account", userAccount);
            resp.sendRedirect("/trip/main.jsp");
        } else {
            req.setAttribute("error", "The supplied credentials were invalid");
            getServletContext().getRequestDispatcher(
                    "/login.jsp").forward(req, resp);
        }
    }

    private void handleRegistration(HttpServletRequest req,
                                    HttpServletResponse resp)
            throws IOException, ServletException {
        String name = req.getParameter("Username");
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");
        String confirmPassword = req.getParameter("ConfirmPassword");
        boolean registered = false;
        try {
            registered = RegistrationManager.registerAccount(name,
                    email,
                    password,
                    confirmPassword);
        } catch (CredentialException e) {
            req.setAttribute("error", e.getMessage());
        } catch (AccountException e) {
            req.setAttribute("error", e.getMessage());
        }
        if (registered) {
            resp.sendRedirect("/trip/login?Registered=true");
        } else {
            getServletContext().getRequestDispatcher(
                    "/register.jsp").forward(req, resp);
        }
    }

    private void handleUpdates(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        Account userAccount =
                (Account) req.getSession().getAttribute("account");
        if (userAccount == null) {
            resp.sendRedirect("/trip/login");
            return;
        }
        if (req.getParameter("Action").equals("email")) {
            String newEmail = req.getParameter("NewEmail");
            try {
                UpdateAccountManger.updateEmail(userAccount, newEmail);
                req.setAttribute("msg", "Your email was updated successfully");
            } catch (AccountException e) {
                req.setAttribute("error", e.getMessage());
            }
        } else if (req.getParameter("Action").equals("password")) {
            String oldPassword = req.getParameter("OldPassword");
            String newPassword = req.getParameter("NewPassword");
            String confirmPassword = req.getParameter("ConfirmPassword");
            try {
                UpdateAccountManger.updatePassword(userAccount,
                        oldPassword,
                        newPassword,
                        confirmPassword);
                req.setAttribute("msg",
                        "Your password was updated successfully");
            } catch (AccountException e) {
                req.setAttribute("error", e.getMessage());
            }
        }
        getServletContext().getRequestDispatcher(
                "/update.jsp").forward(req, resp);
    }
}
