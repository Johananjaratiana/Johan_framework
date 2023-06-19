------------------------------------- JOHAN FRAMEWORK ------------------------------------------
I- Etape 1:
------------
    1) Copiez le principale source du FRAMEWORK :
    --------------------------------------------
        -Placer "JFramework.jar" dans le dossier "WEB-INF/lib/" de votre projet.

    2) Ajoutez les bibliotheques suivants dans "WEB-INF/lib/" de votre projet:
    -------------------------------------------------------------------------
        - asm-9.2.jar
        - commons-beanutils-1.9.4.jar
        - commons-collections-3.2.2.jar
        - commons-loggin-1.2.jar
        - servlet-api.jar


II- Etape 2:
------------
    ==> Mise en point de "web.xml"

    1) Copiez ce contenue dans le "WEB-INF/web.xml" de votre projet :
    ----------------------------------------------------------------
        <servlet>
            <servlet-name>FrontServlet</servlet-name>
            <servlet-class>etu1933.framework.servlet.FrontServlet</servlet-class>
            <init-param>
                <param-name>package_name</param-name>
                <param-value>models</param-value>
            </init-param>
            <init-param>
                <param-name>isConnected</param-name>
                <param-value>isConnected</param-value>
            </init-param>
            <init-param>
                <param-name>auth_session</param-name>
                <param-value>authentification</param-value>
            </init-param>
            <init-param>
                <param-name>session_name</param-name>
                <param-value>sessions</param-value>
            </init-param>
            <init-param>
                <param-name>default_controller</param-name>
                <param-value>default_controller</param-value>
            </init-param>
        </servlet>
        <servlet-mapping>
            <servlet-name>FrontServlet</servlet-name>
            <url-pattern>/</url-pattern>
        </servlet-mapping>

    2) Remplacer le param-value de:
    ------------------------------
        - package_name : models (le nom du package qui contient les classes de vos models)
        - isConnected : isConnected (le nom de votre session pour verifier si l'utilisateur est connecter)
        - auth_session : authentification (le nom de votre session pour stocker la status de l'utilisateur)
        - session_name : sessions (le nom d` attribut """HashMap <String, Object>""" dans chaque classes qui ont besoin de session)
        - default_controller : default_controller (le nom de votre controller par defaut)


III- Etape 3:
-------------
    ==> Methode de programmation

    1) Classe ModelView pour pouvoir:
        - Loader une vue :
            par l` ajout de view :
                exemple : """ mv.setView("index.jsp");"""
                            va nous donner la page """index.jsp"""

        - Envoyer une session :
            par l` attribut session de """ModelView""" :
                exemple : """modelView.addSession("authentification", "admin");"""

        - Envoyer donner vers la vue :
            par l` ajout item :
                exemple : """mv.additem("Personne", personne);"""
                            qui sera accessible par """request.getParameter("Personne")""" dans la vue


    3) Singleton :
        - Annoter les classes Singleton par """@Scope(SingleTon = true)"""

    4) Classe dependant des sessions de l` application :
        - Ajouter une attribut de type ""HashMap<String, Object>"
        - Cette attribut doit avoir le meme nom que celle modifier dans "web.xml"

    5) Fonction associer a son Url : 
        - Annotation requise pour les fonctions qui retournes des """ModelView"""
        - Annoter par exemple """@Url(class_method = "Person-find_all")""" :
            la methode """find_all""" dans la classe """Person"""

    6) Fonction independant de ou des sessions :
        - Annoter par par exemple """@Session(sessionNames = {"session_name_1", "session_name_2"})""" 
            la fonction qui a besion des sessions "session_name_1" et "session_name_2" 
            dans son implementation
        - Ensuite les sessions sont accessible via l` attribut """HashMap<String, Object> sessions"""
            creer dans la classe

    7) Fonction Authentifier :
        - Annoter par exemple """@Auth(name = {"admin", "dg"})""" la fonction qui n` est pas accessible
            que par un utilisateur "admin" ou "dg"
