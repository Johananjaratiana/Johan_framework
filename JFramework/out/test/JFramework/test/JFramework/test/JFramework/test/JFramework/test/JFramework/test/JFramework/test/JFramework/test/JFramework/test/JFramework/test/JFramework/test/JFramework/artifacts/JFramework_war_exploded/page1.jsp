<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String[] liste = (String[])request.getAttribute("Personne"); // Récupération de l'attribut
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Affichage du tableau d'attributs</title>
        <link rel="stylesheet" type="text/css" href="/assets/css/index.css">
<%--        <link rel="stylesheet" type="text/css" href="<%= request.getServletContext().getRealPath("/assets/css/index.css")%>">--%>
    </head>
    <body>
        <div class="test_css">TEST____CSS</div>
        <h1>Envoie de donnes a patir d` une controllers :</h1>
        <ul>
            <% if(liste != null)
            {
                for (int i = 0; i < liste.length ; i++)
                { %>
            <li><%= liste[i] %></li> <!-- Affichage de chaque élément du tableau -->
            <%
                }
            } %>
        </ul>

        <h1>Envoie de donnes a patir d` une formulaire :</h1>
        <form method="post" action="Person-save">
          <input type="hidden" value="13" name="classNumber">
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

        <h1>Envoie de donnes de type tableau :</h1>

        <form method="post" action="Person-tableau">
            <input type="hidden" value="Ca marche" name="null_able">
            <label for="input-name1">Nom 1:</label>
            <input type="date" id="input-name1" name="nom[]" required>
            <br>
            <label for="input-name2">Nom 2:</label>
            <input type="date" id="input-name2" name="nom[]" required>
            <br>
            <label for="input-name3">Nom 3:</label>
            <input type="date" id="input-name3" name="nom[]" required>
            <br>
            <input type="submit" value="Enregistrer">
        </form>

        <h1>Envoie fichier :</h1>
        <form method="post" enctype="multipart/form-data">
            <input type="file" name="file1">
            <input type="submit" value="Envoyer">
        </form>
    </body>
</html>
