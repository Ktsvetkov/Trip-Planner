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

