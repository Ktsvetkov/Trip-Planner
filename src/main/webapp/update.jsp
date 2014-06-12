<%@ page import="java.util.*" %>

<html>
    <head>
    <title>Toto Web APP</title>
    <link rel="stylesheet" type="text/css" href="stylesheets/main.css">
    <% if (request.getSession().getAttribute("account") == null) {%>
    <meta http-equiv="refresh" content="0; url=login.jsp" />
    <% } %>
    </head>

    <body>

    <!-- Start tool bar-->

    <div id="nav">
        <ul>

            <li><a href="/trip/main.jsp">Home</a></li>
            <li style="background-color: #fff">
                <a href="/trip/update.jsp"><font color="#c00">Update Info</font></a>
            </li>
            <li><a href="/trip/logout">Logout</a></li>

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
    <div id="login" class="centered">
        <form method="post" action="update">
            <h3>Update Account Information</h3>
            </br>
            <h4>Enter Old Email Address</h4>
            <input type="text" name="OldEmail" placeholder="Old Email">
            <h4>Enter New Email Address</h4>
            <input type="text" name="NewEmail" placeholder="New Email">
            </br>
            <input type="text" name="ConfirmEmail" placeholder="Confirm New Email">
            </br>
            <h4>Enter Old Password</h4>
            <input type="password" name="OldPassword" placeholder="Old Password">
            <h4>Enter New Password</h4>
            <input type="password" name="NewPassword" placeholder="New Password">
            </br>
            <input type="password" name="ConfirmPassword" placeholder="Confirm New Password">
            </br>
            <input type="submit" value="Update">
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