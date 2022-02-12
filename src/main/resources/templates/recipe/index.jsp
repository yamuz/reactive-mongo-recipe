<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Привет от Амиго</title>
</head>
<body>
<%--это еще HTML--%>
<%
    //А тут можно писать Java-код

    String s = "Вся власть роботам!";
    for(int i=0; i<10; i++)
    {
        System.out.println(s);
        System.out.println("<br>");
    }

%>
<%--а это уже опять HTML--%>
</body>
</html>