package edu.gatech.cs2340.trip.controller;

/**
 * Created by dheavern on 6/3/14.
 */
import edu.gatech.cs2340.trip.model.Account;
import edu.gatech.cs2340.trip.model.AccountDAO;
import edu.gatech.cs2340.trip.model.SqlliteAccountDAO;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns =
        {"/accounts",
         "/register",
         "/find"
        })
public class AccountManagementServlet extends HttpServlet {
    private AccountDAO acd = new SqlliteAccountDAO();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
        throws IOException, ServletException {
        String result;

        /*if (request.getRequestURI() == "/trip/register") {
            Account acc = new Account(request.getParameter("accountname"), request.getParameter("password"));
            try {
                acd.insertAccount(acc);
                request.setAttribute("test", "Made Account");
            } catch (Exception e) {
                request.setAttribute("test", "Didnt make account");
            }
        } else if (request.getRequestURI() == "/trip/find") {

        }*/
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/accounts.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("test", "Asdas");
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher("/accounts.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return;
    }
}
