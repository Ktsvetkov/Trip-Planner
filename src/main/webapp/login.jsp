<%@ page import="java.util.*" %>

<<<<<<< HEAD
<html>
    <head>
        <title>Toto Web APP</title>
        <% if (request.getSession().getAttribute("account") != null) { %>
        <meta http-equiv="refresh" content="0; url=main.jsp" />
        <% } %>
        <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
    </head>

    <body>

    <!-- Start tool bar-->

    <div id="nav">
        <ul>
            <li><a href="/trip/main.jsp">Home</a></li>
            <li style="background-color: #fff"><a href="#"><font color="#c00">Login</a></font></li>
            <li><a href="/trip/register.jsp">Register</a></li>

            <li>
                <form id="tfnewsearch" method="get" action="#">
                    <input type="text" class="tftextinput" name="q" size="21" maxlength="120">
                    <input type="submit" value="search" class="tfbutton">
                </form></li>
        </ul>
    </div>

    <!-- End tool bar-->
=======
    <jsp:include page="header.jsp" />
>>>>>>> 70953e0f472568e28e8d3282e4c9a6b1858cd8f2

    <!-- Start content-->
        </br>
        </br>
        </br>
        </br>
        </br>
    <center>
    <div id="login" class="centered" >
        <form method="post" action="login">
            <h3>Login</h3>
            <input type="text" name="Username" placeholder="Username or email">
            </br>
            <input type="password" name="Password" placeholder="Password">
            </br>
            <input type="submit" value="Login">
            </br>
            <a href="/trip/register.jsp">New User?</a>

        </form>
        <% if (request.getAttribute("error") != null) { %>
        <div class="error">
            <%= request.getAttribute("error") %>
        </div>
        <% } %>
    </div>
    </center>

    <!-- End content-->

    </body>
</html>

