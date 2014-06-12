<%@ page import="java.util.*" %>

    <jsp:include page="header.jsp" />

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

