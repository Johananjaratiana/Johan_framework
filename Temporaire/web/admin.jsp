<%@ page import="java.sql.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    Date[] dates = (Date[]) request.getAttribute("SendData");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Access with admin</title>
</head>
<body>
    <h1>Admin access is success</h1>
    <p>With data sending :</p>
    <ul>
        <% for (int i = 0; i < dates.length ; i++){ %>
        <li><%= dates[i] %></li> <!-- Affichage de chaque élément du tableau -->
        <% } %>
        <li></li>
    </ul>
</body>