#!/bin/bash

# Spécifiez le chemin du projet NetBeans
java_path="/home/johan/Documents/Programmation/Naina/Framework/JFramework/src/java"

# Chemin vers le dossier temporaire projet
PROJECT_PATH="/home/johan/Documents/Programmation/Naina/Framework/Temporaire/web"

# Dossier des classes temporaire
classes_path="$PROJECT_PATH/WEB-INF/classes"
rm -R "${classes_path}"
mkdir "${classes_path}"

# Dossier des librairies temporaire
lib_path="$PROJECT_PATH/WEB-INF/lib/"
rm -R "$lib_path"
mkdir "$lib_path"

# Chmod
chmod 777 -R "${lib_path}"
chmod 777 -R "${classes_path}"

#Copier les librairairies utils
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-beanutils-1.9.4.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-collections-3.2.2.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-logging-1.2.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/servlet-api.jar "$lib_path"
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/asm-9.2.jar "$lib_path"
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/gson/gson-2.10.jar "$lib_path"

# Compiler les fichiers .java vers la "classes_path"   /  *: specifie tout les .jar present et /: tout les .classe deja compile
javac 						-d "${classes_path}" "${java_path}/annotation_J/Url.java"			                -Xlint -Xdiags:verbose
javac 						-d "${classes_path}" "${java_path}/annotation_J/Scope.java"			                -Xlint -Xdiags:verbose
javac 						-d "${classes_path}" "${java_path}/annotation_J/Auth.java"			                -Xlint -Xdiags:verbose
javac 						-d "${classes_path}" "${java_path}/annotation_J/Session.java"		                -Xlint -Xdiags:verbose
javac 						-d "${classes_path}" "${java_path}/annotation_J/restAPI.java"		                -Xlint -Xdiags:verbose
javac 						-d "${classes_path}" "${java_path}/etu1933/framework/Singleton.java"	            -Xlint -Xdiags:verbose
javac 						-d "${classes_path}" "${java_path}/etu1933/framework/Mapping.java"		            -Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*" 			-d "${classes_path}" "${java_path}/etu1933/framework/view/ModelView.java"	-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*"			-d "${classes_path}" "${java_path}/helpers_J/ArgumentNamesExtractor.java"	-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*"			-d "${classes_path}" "${java_path}/etu1933/framework/file/FileUpload.java"	-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*"			-d "${classes_path}" "${java_path}/helpers_J/MyCast.java"			        -Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*:${classes_path}"	-d "${classes_path}" "${java_path}/helpers_J/MySession.java"		-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*:${classes_path}" 	-d "${classes_path}" "${java_path}/helpers_J/MethodLoader.java"		-Xlint -Xdiags:verbose
javac -cp ".:${classes_path}" 			-d "${classes_path}" "${java_path}/helpers_J/Authentification.java"		-Xlint -Xdiags:verbose
javac -cp ".:${classes_path}" 			-d "${classes_path}" "${java_path}/helpers_J/Init.java"			        -Xlint -Xdiags:verbose

javac -cp ".:${lib_path}*:${classes_path}" 	-d "${classes_path}" "${java_path}/etu1933/framework/servlet/FrontServlet.java"	-Xlint:-serial	-Xlint 



# --------------------------- MIS EN .JAR DES CLASSES ---------------------------------
jar cf JFramework.jar -C "${classes_path}" .
# --------------------------- MIS EN .JAR DES CLASSES ---------------------------------


# --------------------------- COPIE DU .JAR VERS LE PROJET TEMPORAIRE ---------------------------
# Deplacer vers le librairies
mv JFramework.jar "$lib_path"
rm -R "${classes_path}"
# --------------------------- COPIE DU .JAR VERS LE PROJET TEMPORAIRE ---------------------------





# -------------------------------------------------- COMPILATION DU PROJET TEMPORAIRE -----------------------------------
java_path="/home/johan/Documents/Programmation/Naina/Framework/Temporaire/java"

# Dossier des classes temporaire
mkdir "${classes_path}"

# Compiler les fichiers .java vers la "classes_path"   /  *: specifie tout les .jar present et /: tout les .classe deja compile
find "${java_path}" -name "*.java" -print0 | xargs -0 javac -parameters -cp ".:${lib_path}*" -d "${classes_path}" -Xlint:-serial -Xlint -Xdiags:verbose
# -------------------------------------------------- COMPILATION DU PROJET TEMPORAIRE -----------------------------------


# --------------------------- MISE EN .WAR ---------------------------
# Chemin de sortie pour le fichier WAR
WAR_OUTPUT_PATH="JTeste.war"
# Exporter le projet en tant que WAR
jar cvf "$WAR_OUTPUT_PATH" -C "$PROJECT_PATH" .
# Copie de fichier war vers tomcat9
sudo mv "$WAR_OUTPUT_PATH" /var/lib/tomcat9/webapps/

# --------------------------- MISE EN .WAR ---------------------------


