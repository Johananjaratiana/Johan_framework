<%@page import="models.Person"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Person[] famille = (Person[])request.getAttribute("famille");
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
                <td><%= famille[i].getNom().split(" ")[0]%></td>
                <td><%= famille[i].getNom().split(" ")[1]%></td>
            </tr>
            <% } %>
        </table>
        <h2>Ajouter nouvelle membre</h2>
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
