<%@ page import="edu.gatech.cs2340.trip.model.Account" %>
<html>
<body>

    <% if(request.getSession().getAttribute("account") == null) { %>
        <a href="/trip/login"> Login </a>
    <% } else {
      	Account userAccount = (Account) request.getSession().getAttribute("account");
      	out.println("You are logged in as: " + userAccount.getName());
    } %>
</body>
</html>