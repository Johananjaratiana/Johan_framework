<%@page import="models.Personne"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Personne[] famille = (Personne[])request.getAttribute("famille");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            <tr>
                <td>Nom</td>
                <td>Prenom</td>
            </tr>
            <% for(int i = 0 ; i < famille.length ; i++) { %>
            <tr>
                <td><%= famille[i].getName()%></td>
                <td><%= famille[i].getUserName()%></td>
            </tr>
            <% } %>
        </table>
    </body>
</html>
