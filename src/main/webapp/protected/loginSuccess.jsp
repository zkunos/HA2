<%@ page import="static edu.corvinus.ha2.Helpers.getDate" %>
<%@ page import="static edu.corvinus.ha2.Helpers.getDayText" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello </title>
</head>
<body>
<%
    String email = null;
    String name = null;
    String sessionID = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("email")) email = cookie.getValue();
            if (cookie.getName().equals("name")) name = cookie.getValue();
            if (cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
        }
    }
%>

<%--<h1><%= "Network Technologies (293NBISK603M): Home assignment-2, 2022 spring semester" %></h1>--%>
<br/>
<br/>
<p style="float:right">
    <%=   getDate() %>
</p>
<img src="<%= request.getContextPath() + "/images/" + getDayText() %>.png" alt="name of the day" width="100" height="50"
     style="float:left">
<br/>
<br/>
<h2>Hello <%= name %>,</h2>
<h4>Your email = <%= email %>
</h4>
<h4>Session ID = <%= sessionID %>
</h4>

<br/>
<form action="<%= request.getContextPath() %>/LogoutServlet" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>