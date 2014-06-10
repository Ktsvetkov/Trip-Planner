<%@ page import="java.util.*" %>

<html>
    <head>
        <title>Toto Web APP</title>
        <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
    </head>

    <body>

    <!-- Start tool bar-->

    <div id="nav">
        <ul>
            <li style="background-color: #fff"><a href="#"><font color="#c00">Login</a></font></li>
            <li><a href="register.jsp">Register</a></li>
            <li><a href="#">About Us</a></li>

            <li>
                <form id="tfnewsearch" method="get" action="#">
                    <input type="text" class="tftextinput" name="q" size="21" maxlength="120">
                    <input type="submit" value="search" class="tfbutton">
                </form></li>
        </ul>
    </div>

    <!-- End tool bar-->

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
        </br>
            <input type="submit" value="login">
            </br>
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

