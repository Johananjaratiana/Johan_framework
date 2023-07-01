<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String session___ = (String) session.getAttribute("createdSession");
    String attributs = (String) request.getAttribute("requiredSession");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Access with client</title>
</head>
<body>
    <h1>Client access is success</h1>
    <p>With session sending : <%= session___+" - - "+attributs%></p>
</body>