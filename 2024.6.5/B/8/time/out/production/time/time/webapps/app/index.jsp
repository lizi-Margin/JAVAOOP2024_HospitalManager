<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Current Time</title>
</head>
<body>
    <h1>Current Time</h1>
    <p>The current time is: 
        <%
            java.util.Date date = new java.util.Date();
            out.println(date.toString());
        %>
    </p>
</body>
</html>
