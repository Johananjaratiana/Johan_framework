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
        <form method="post" action="Person-save">
          <label for="input-name">Nom :</label>
              <input type="text" id="input-name" name="nom" required>
          <br>
          <label for="input-date">Date de naissance :</label>
            <input type="date" id="input-date" name="dateNaissance" required>
          <br>
          <label for="input-email">Email :</label>
              <input type="email" id="input-email" name="email" required>
          <br>
          <label for="input-address">Adresse :</label>
              <textarea id="input-address" name="adresse" required></textarea>
          <br>
          <input type="submit" value="Enregistrer">
        </form>

    </body>
</html>
