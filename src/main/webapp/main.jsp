<%@ page import="edu.gatech.cs2340.trip.model.Account" %>
<html>
<body>
	<%
  	Account userAccount = (Account) request.getAttribute("account");
  	out.println("Servlet to JSP communication of an object: " + userAccount.getName());
	%>
<% if(request.getSession().getAttribute("user") == null) { %>
<a href="/trip/login.jsp"> Login </a>
<% } %>
</body>
</html>