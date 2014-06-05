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
            <li><a href="#">Register</a></li>
            <li><a href="#">FAQs</a></li>
            <li><a href="#">Contact</a></li>
            <li><a href="#">Login</a></li>
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
        <form class="centered" method="post" action="login">
            <h3> User Name </h3>
            <input type="text" name="Username">
            </br>
            </br>
            <h3> Password </h3>
            <input type="password" name="Password">
            </br>
            </br>
            <input type="submit" value="Login">
        </form>
        <%= request.getAttribute("error") %>

        <form class="centered" method="post" action="register">
        <input type="submit" value="make test account">
        </form>


    <!-- End content-->

    </body>
</html>

