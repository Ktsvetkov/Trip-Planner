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
            <li><a href="login.jsp"><font color="#c00">Login</a></font></li>
            <li  style="background-color: #fff"><a href="#">Register</a></li>
            <li><a href="#">About Us</a></li>

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
        <form method="post" action="/register">
            <h3>Register</h3>
            <input type="text" name="Username" placeholder="Username">
            </br>
            <input type="text" name="Email" placeholder="Email">
            </br>
            <input type="text" name="Password" placeholder="Password">
            </br>
            <input type="submit" value="Register">
            </br>
        </form>
    </div>
    </center>

    <!-- End content-->

    </body>
</html>

