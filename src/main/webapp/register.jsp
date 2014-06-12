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
            <li><a href="/trip/main.jsp">Home</a></li>
            <li><a href="/trip/login.jsp">Login</a></li>
            <li  style="background-color: #fff"><a href="#"><font color="#c00">Register</a></font></li>

            <li>
                <form id="tfnewsearch" method="get" action="#">
                    <input type="text" class="tftextinput" name="q" size="21" maxlength="120">
                    <input type="submit" value="search" class="tfbutton">
                </form>
            </li>
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
        <form method="post" action="register">
            <h3>Register</h3>
            <input type="text" name="Username" placeholder="Username">
            </br>
            <input type="text" name="Email" placeholder="Email">
            </br>
            <input type="password" name="Password" placeholder="Password">
            </br>
            <input type="password" name="ConfirmPassword" placeholder="Confirm Password">
            </br>
            <input type="submit" value="Register">
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

