<%@ page import="edu.gatech.cs2340.trip.model.Account" %>
<html>
<body>
<% if(request.getSession().getAttribute("user") == null) { %>
<a href="/trip/login.jsp"> Login </a>
<% } %>
</body>
</html>