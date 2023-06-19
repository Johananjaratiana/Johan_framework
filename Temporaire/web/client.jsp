<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String createdSession = request.getSession().getAttribute("createdSession");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Access with client</title>
</head>
<body>
    <h1>Client access is success</h1>
    <p>With session sending : <%= createdSession%></p>
</body>