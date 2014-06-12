 <%@ page import="java.util.*" %>

 <html>
    <head>
        <title>Toto Web APP</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
    </head>

    <body>

    <div id="nav">
        <ul>
            <li><a href="/trip/main.jsp">Home</a></li>
            <% if(request.getSession().getAttribute("account") == null) { %>
                    <li><a href="/trip/login"> Login </a></li>
            <% } else { %>
                    <li><a href="/trip/logout"> Logout </a></li>
                    <li><a href="/trip/update.jsp"> Update Account </a></li>
            <% } %>
            <li><a href="/trip/about.jsp">About Us</a></li>

        </ul>
    </div>


