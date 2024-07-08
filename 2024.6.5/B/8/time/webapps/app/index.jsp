<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Current Time</title>
</head>
<body style="background-color: #c3f8f8;">
    <%
        java.time.LocalDateTime  now = java.time.LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
    %>
    <h1>当前时间为: 
        <%
            out.println(String.valueOf( hour)+":"+String.valueOf(minute)+":"+String.valueOf(second));
        %>
    </h1>
    <p>
        <div>
            当前年份:
            <%
                out.println(year);
            %>
        </div>
        

        <div>
            当前日期:
            <%
                out.println(String.valueOf(month)+"月"+ String.valueOf(day)+"日" );
            %>
        </div>

        <div>
            当前时间:
            <%
                out.println(String.valueOf( hour)+":"+String.valueOf(minute)+":"+String.valueOf(second));
            %>
        </div>
    </p>
</body>
</html>
