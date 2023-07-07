<!DOCTYPE html>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Notes </title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/owl.carousel.css">
        <link rel="stylesheet" href="assets/css/animate.css">
        <link rel="stylesheet" href="assets/css/main.css">
        <!-- Responsive Stylesheet -->
        <link rel="stylesheet" href="assets/css/responsive.css">
    </head>

    <body id="body">

	    <!-- 
	    Header start
	    ==================== -->

	    <section id="hero-area">
	        <div class="container">
	            <div class="row">
	                <div class="col-md-6">
	                    <div class="block">
	                        <h1 class="wow fadeInDown">Johan `S Framework</h1>
	                        <p class="wow fadeInDown" data-wow-delay="0.3s">Liser bien les instructions</p>
	                        <div class="wow fadeInDown" data-wow-delay="0.3s">
	                        	<a class="btn btn-default btn-home" href="#about" role="button">Commencer maintenant</a>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-md-6 wow zoomIn">
	                    <div class="block">
	                        <div class="counter text-center">
	                            <ul id="countdown_dashboard">
	                                <li>
	                                    <div class="dash days_dash">
	                                        <div class="digit">0</div>
	                                        <div class="digit">0</div>
	                                        <div class="digit">0</div>
	                                        <span class="dash_title">Days</span>
	                                    </div>
	                                </li>
	                                <li>
	                                    <div class="dash hours_dash">
	                                        <div class="digit">0</div>
	                                        <div class="digit">0</div>
	                                        <span class="dash_title">Hours</span>
	                                    </div>
	                                </li>
	                                <li>
	                                    <div class="dash minutes_dash">
	                                        <div class="digit">0</div>
	                                        <div class="digit">0</div>
	                                        <span class="dash_title">Minutes</span>
	                                    </div>
	                                </li>
	                                <li>
	                                    <div class="dash seconds_dash">
	                                        <div class="digit">0</div>
	                                        <div class="digit">0</div>
	                                        <span class="dash_title">Seconds</span>
	                                    </div>
	                                </li>
	                            </ul>
	                        </div>
	                    </div>
	                </div>
	            </div><!-- .row close -->
	        </div><!-- .container close -->
	    </section><!-- header close -->

        <section id="call-to-action" class="section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12 wow text-center">
                        <div class="block">
                            <h2>Envoyer des comentaires</h2>
                            <p>Vos commentaires nous aides à mieux performer les fonctionalités de ce framework</p>
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="Enter Your Email Address">
                                <button class="btn btn-default btn-submit" type="submit">Commenter</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section><!-- #call-to-action close -->

        <!-- 
        Contact start
        ==================== -->
        <section id="contact" class="section">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-12">
                        <div class="block">
                            <div class="heading wow fadeInUp">
                                <h2>Fonctionalités</h2>
                                <h3>I- Etape 1:</h3>
                                <p>    
                                    1) Copiez le principale source du FRAMEWORK :
                                    --------------------------------------------
                                        -Placer "JFramework.jar" dans le dossier "WEB-INF/lib/" de votre projet.
                                
                                    2) Ajoutez les bibliotheques suivants dans "WEB-INF/lib/" de votre projet:
                                    -------------------------------------------------------------------------
                                        - gson-2.10.jar
                                        - asm-9.2.jar
                                        - commons-beanutils-1.9.4.jar
                                        - commons-collections-3.2.2.jar
                                        - commons-loggin-1.2.jar
                                        - servlet-api.jar
                                </p>
                                <h3>II- Etape 2:</h3>
                                <p>
                                    ==> Mise en point de "web.xml"

                                    1) Copiez ce contenue dans le "WEB-INF/web.xml" de votre projet :
                                    ----------------------------------------------------------------
                                        <servlet>
                                            <servlet-name>FrontServlet</servlet-name>
                                            <servlet-class>etu1933.framework.servlet.FrontServlet</servlet-class>
                                            <init-param>
                                                <param-name>excludeFolders</param-name>
                                                <param-value>static</param-value>
                                            </init-param>
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
                                                <param-value></param-value>
                                            </init-param>
                                        </servlet>
                                        <servlet-mapping>
                                            <servlet-name>FrontServlet</servlet-name>
                                            <url-pattern>/</url-pattern>
                                        </servlet-mapping>
                                        <servlet-mapping>
                                            <servlet-name>default</servlet-name>
                                            <url-pattern>/static/*</url-pattern>
                                        </servlet-mapping>
                                
                                    2) Remplacer le param-value de:
                                    ------------------------------
                                        - excludeFolders : static (un mot ou tout les "url" qui contient ce mot ne soit pas traiter par le servlet)
                                        - package_name : models (le nom du package qui contient les classes de vos models)
                                        - isConnected : isConnected (le nom de votre session pour verifier si l'utilisateur est connecter)
                                        - auth_session : authentification (le nom de votre session pour stocker la status de l'utilisateur)
                                        - session_name : sessions (le nom d` attribut """HashMap <String, Object>""" dans chaque classes qui ont besoin de session)
                                        - default_controller : default_controller (le nom de votre controller par defaut)
                                
                                    3) Concernant les styes commes css, js, image, ...:
                                    --------------------------------------------------
                                        - Remplacer l` url-pattern de la servlet-mapping de "default" par le nom de votre dossier qui contient tout ces éléments.
                                
                                </p>
                                <h3>III- Etape 3:</h3>
                                <p>
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
                                
                                    7) Fonction avec Authentification :
                                        - Annoter par exemple """@Auth(name = {"admin", "dg"})""" la fonction qui n` est pas accessible
                                            que par un utilisateur "admin" ou "dg"
                                
                                    8) Fonction qui retourne des JSON :
                                        - Pour les fonction qui ne retournent pas des modelview :
                                            Annoter par """@restAPI""" les fonction qui ne retourne pas des modelView
                                        - Pour les fonction qui ne retournent pas des modelview :
                                            Aplleller comme la suite la fonction de l` objet modelview : """modelView.setJson(true);"""
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-5 wow fadeInUp">
						<div class="block text-left">
							<div class="sub-heading">
								<h4>Contact Address</h4>
								<p>ITU-Andoharanofotsy</p>
							</div>
							<address class="address">
                                <hr>
                                <p><strong>E:</strong>&nbsp;johanandrianaivosoa@gmail.com<br>
                                <strong>P:</strong>&nbsp;+264 3489 78786</p>
								
                                
							</address>
						</div>
                    </div>

                    <div class="col-xs-12 col-sm-12 col-md-5 col-md-offset-1 wow fadeInUp" data-wow-delay="0.3s">
                    	<div class="form-group">
                    	    <form action="#" method="post" id="contact-form">
                    	        <div class="input-field">
                    	            <input type="text" class="form-control" placeholder="Your Name" name="name">
                    	        </div>
                    	        <div class="input-field">
                    	            <input type="email" class="form-control" placeholder="Email Address" name="email">
                    	        </div>
                    	        <div class="input-field">
                    	            <textarea class="form-control" placeholder="Your Message" rows="3" name="message"></textarea>
                    	        </div>
                    	        <button class="btn btn-send" type="submit">Send me</button>
                    	    </form>

                    	    <div id="success">
                    	        <p>Your Message was sent successfully</p>
                    	    </div>
                    	    <div id="error">
                    	        <p>Your Message was not sent successfully</p>
                    	    </div>
                    	</div>
                    </div>
                </div>
            </div>
        </section>

        <section clas="wow fadeInUp">
        	<div class="map-wrapper">
        	</div>
        </section>

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="block">
                            <p>J Copyright &copy;| All right reserved.</p>
                        </div>
                    </div>
                </div>
            </div>
        </footer>


        <!-- Js -->
        <script src="assets/js/vendor/modernizr-2.6.2.min.js"></script>
        <script src="assets/js/vendor/jquery-1.10.2.min.js"></script>
        <script src="assets/js/jquery.lwtCountdown-1.0.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/owl.carousel.min.js"></script>
        <script src="assets/js/jquery.validate.min.js"></script>
        <script src="assets/js/jquery.form.js"></script>
        <script src="assets/js/jquery.nav.js"></script>
        <script src="assets/js/jquery.sticky.js"></script>
        <script src="assets/js/plugins.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/main.js"></script>
        
    </body>
</html>
