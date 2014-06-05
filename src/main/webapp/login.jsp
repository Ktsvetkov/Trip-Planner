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
            <li><a href="#">Register</a></li>
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

    <div id="login" class="leftAligned" >
        <form method="post" action="#">
            <h3>Login</h3>
            <input class="username" type="text" name="Username" placeholder="Username or email">
            </br>
            <input class="password" type="text" name="Password" placeholder="Password">

        </br>
        </br>

    </div>

    <div id="signup" class="centered" >
        <form method="post" action="#">
            <h3>Sign Up</h3>
            <input class="username" type="text" name="Email" placeholder="Email">
            </br>
            <input class="username" type="text" name="Username" placeholder="Username">
            </br>
            <input class="password" type="text" name="Password" placeholder="Password">
            </br>
            </br>
            <input type="submit" value="Sign Up">
        </form>
        </br>
        </br>

    </div>


    <!-- End content-->

    </body>
</html>

