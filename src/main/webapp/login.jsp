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
        <form method="post" action="register">
            <input type="text" name="accountname">
            <input type="text" name="password">
            <input type="submit" value="register">
        </form>

        </br>

        <form method="post" action="find">
            <input type="text" name="accountname">
            <input type="submit" value="find">
        </form>

        <%= request.getAttribute("test") %>

    <!-- End content-->

    </body>
</html>

