<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String[] liste = (String[])request.getAttribute("Personne"); // Récupération de l'attribut
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Affichage du tableau d'attributs</title>
    </head>
    <body>
        <h1>Les éléments du tableau sont :</h1>
        <ul>
            <% for (int i = 0; i < liste.length ; i++) { %>
                <li><%= liste[i] %></li> <!-- Affichage de chaque élément du tableau -->
            <% } %>
        </ul>
    </body>
</html>
