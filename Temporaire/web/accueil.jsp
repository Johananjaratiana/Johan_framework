<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Affichage du tableau d'attributs</title>
        <link rel="stylesheet" type="text/css" href="/assets/css/index.css">
    </head>
<body>

    <h1>"Admin" Envoie de donnes de type tableau :</h1>

    <form method="post" action="Person-ReceiveData">
        <label for="input-name1">Nom 1:</label>
        <input type="date" id="input-name1" name="dates[]" required>
        <br>
        <label for="input-name2">Nom 2:</label>
        <input type="date" id="input-name2" name="dates[]" required>
        <br>
        <label for="input-name3">Nom 3:</label>
        <input type="date" id="input-name3" name="dates[]" required>
        <br>
        <input type="submit" value="Enregistrer">
    </form>

    <h1>"Client" Envoie fichier :</h1>

    <form method="post" action="Person-Client" enctype="multipart/form-data">
        <input type="file" name="file1">
        <input type="submit" value="Envoyer">
    </form>

    <h1>"Both" Link</h1>
    <a href="Person-Both">Teste both authentification</a>

    <h1>"Both" Session data</h1>
    <a href="Person-BothSessionData">Teste both authentification</a>

    <h1>"Both" Both Session ModelView Data</h1>
    <a href="Person-BothSessionModelViewData">Teste both authentification</a>
</body>
</html>
