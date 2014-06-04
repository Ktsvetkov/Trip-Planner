    <%@ page import="java.util.*" %>
<form method="post" action="register">
    <input type="text" name="accountname">
    <input type="text" name="password">
    <input type="submit" value="register">
</form>
<form method="post" action="find">
    <input type="text" name="accountname">
    <input type="submit" value="find">
</form>
<br />
<%= request.getAttribute("test") %>
