<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Acceuil</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/Login-Form-Basic-icons.css">
        <link rel="stylesheet" href="assets/css/Ludens---1-Index-Table-with-Search--Sort-Filters-v20.css">
    </head>
<body>

    <div class="container">

        <a href="Login-Documentation">Documentation</a>

        <div class="row" style="padding-block: 5%;">
            <form action="Login-change" method="get" class="row col-md-10">
                <h1 class="col-md-6">User` s type : <span style="color: green;"><%= session.getAttribute("authentification")%></span></h1>
                <button type="submit" class="btn btn-primary col-md-2 offset-md-2">Change session</button>
            </form>
            <form action="Login-logout" method="get" class="col-md-1">
                <button class="btn btn-danger">
                    Déconnecter
                </button>
            </form>
        </div>

        <hr>

        <div class="row" style="padding-block: 5%;">
            <form action="Login-removeOrAddSession" method="get" class="row">
                <h1 class="col-md-6">Temporary` s session : <span style="color: green;"><%= session.getAttribute("temporary")%></span></h1>
                <button type="submit" class="btn btn-primary col-md-4 offset-md-2">Remove or add specific session</button>
            </form>
        </div>

        <hr>

        <div style="padding-block: 5%;">
            <h1>"Admin" Envoie de donnes de type tableau :</h1>
            <form method="post" action="Person-ReceiveData" class="form-control">
                <table class="table">
                    <tr>
                        <th>Date 1</th>
                        <th>Date 2</th>
                        <th>Date 3</th>
                        <th></th>
                    </tr>
                    <tr>
                        <td><input type="date" class="form-control" name="dates[]" required></td>
                        <td><input type="date" class="form-control" name="dates[]" required></td>
                        <td><input type="date" class="form-control" name="dates[]" required></td>
                        <td><input type="submit" class="btn btn-primary" value="Enregistrer" style="background-color: green;color: whitesmoke;">
                    </tr>

                </table>
            </form>
        </div>
        
        <hr>

        <div style="padding-block: 5%;">
            <h1>"Client" Envoie fichier :</h1>
            <form method="post" action="Person-Client" enctype="multipart/form-data" class="row">
                <div class="input-group col-md-6">
                    <input type="file" name="file1" class="form-control">
                    <label class="input-group-text" for="input-name1">Choisir un fichier</label>
                </div>
                <input type="submit" value="Envoyer" class="btn btn-primary" style="background-color: green;color: whitesmoke;">
            </form>
        </div>
        
        <hr>

        <table class="table">
            <tr>
                <th>"Both" Link</th>
                <th>"Both" Session data</th>
                <th>"Both" Both Session ModelView Data</th>
            </tr>
            <tr>
                <td><a href="Person-Both">Voir</a></td>
                <td><a href="Person-BothSessionData">Voir</a></td>
                <td><a href="Person-BothSessionModelViewData">Voir</a></td>
            </tr>
        </div>

    </div>
</body>
</html>
