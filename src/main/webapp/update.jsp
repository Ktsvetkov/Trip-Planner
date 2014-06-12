<%@ page import="java.util.*" %>
    <%
    if(request.getSession().getAttribute("account") == null) {
        response.sendRedirect("/trip/login");
    }
     %>
    <jsp:include page="header.jsp" />
    <!-- Start content-->
        </br>
        </br>
        </br>
        </br>
        </br>
    <center>
    <div id="update" class="centered">
        <h3>Update Account Information</h3>
        </br>
        <h4> Update Email </h4>
        <form method="post" action="update">
            <input type="hidden" name="Action" value="email">
            <h4>Enter New Email Address</h4>
            <input type="text" name="NewEmail" placeholder="New Email">
            <input type="submit" value="Change Email">
        </form>
        <h4> Change Password </h4>
        <form method="post" action="update">
            <input type="hidden" name="Action" value="password">
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