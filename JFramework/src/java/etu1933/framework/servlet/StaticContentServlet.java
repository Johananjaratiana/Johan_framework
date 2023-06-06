package etu1933.framework.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StaticContentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestedFile = request.getRequestURI().substring(request.getContextPath().length());

        // Chemin physique vers le dossier contenant les fichiers CSS
        String basePath = "/home/johan/Documents/Programmation/Naina/Framework/JFramework/web/assets/css/";

        Path file = Paths.get(basePath, requestedFile);
        if (Files.exists(file)) {
            // Définir le type de contenu pour la réponse
            response.setContentType("text/css");
            // Lire le contenu du fichier et l'écrire dans la réponse
            Files.copy(file, response.getOutputStream());
        } else {
            // Fichier introuvable, renvoyer une réponse 404
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}