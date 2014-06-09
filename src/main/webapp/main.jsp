<%@ page import="edu.gatech.cs2340.trip.model.Account" %>
<html>
<body>

    <% if(request.getSession().getAttribute("account") == null) { %>
        <a href="/trip/login"> Login </a>
    <% } else {
      	Account userAccount = (Account) request.getSession().getAttribute("account");
      	out.println("Servlet to JSP communication of an object: " + userAccount.getName());
    } %>
</body>
</html>