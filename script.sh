#!/bin/bash

# Spécifiez le chemin du projet NetBeans
java_path="/home/johan/Documents/Programmation/Naina/Framework/JFramework/build/web/WEB-INF/classes"

# Chemin vers le dossier temporaire projet
PROJECT_PATH="/home/johan/Documents/Programmation/Naina/Framework/Temporaire/web"

<<<<<<< Updated upstream
# Spécifiez le nom du package contenant les fichiers .class que vous voulez compiler
package_name1="annotation_J"
package_name2="helpers_J"
package_name3="etu1933"

# Copiez les fichiers .class compilés dans un dossier spécifié
target_path="classes"
mkdir "$target_path"
cp -r "$java_path/$package_name1" "$target_path"
cp -r "$java_path/$package_name2" "$target_path"
cp -r "$java_path/$package_name3" "$target_path"

# Mettre en .jar
jar cf JFramework.jar -C classes .

rm -R classes

# Deplacer vers le librairies
mv JFramework.jar "$PROJECT_PATH/lib"



# ---------------------------------- WAR --------------------------
# Nom du projet
PROJECT_NAME="JTeste"
=======
# Dossier des classes temporaire
classes_path="$PROJECT_PATH/WEB-INF/classes"
rm -R "$classes_path"
mkdir "$classes_path"

# Dossier des librairies temporaire
lib_path="$PROJECT_PATH/WEB-INF/lib/"
rm -R "$lib_path"
mkdir "$lib_path"

#Copier les librairairies utils
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-beanutils-1.9.4.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-collections-3.2.2.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-logging-1.2.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/servlet-api.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/asm-9.2.jar "$lib_path"

# Compiler les fichiers .java vers la "classes_path"   /  *: specifie tout les .jar present et /: tout les .classe deja compile
javac 						-d "$classes_path" "$java_path/annotation_J/Url.java"			-Xlint -Xdiags:verbose
javac 						-d "$classes_path" "$java_path/etu1933/framework/Mapping.java"		-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*" 			-d "$classes_path" "$java_path/etu1933/framework/view/ModelView.java"	-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*"			-d "$classes_path" "$java_path/helpers_J/ArgumentNamesExtractor.java"	-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*"			-d "$classes_path" "$java_path/etu1933/framework/file/FileUpload.java"	-Xlint -Xdiags:verbose
javac -cp ".:${classes_path}" 			-d "$classes_path" "$java_path/helpers_J/Init.java"			-Xlint -Xdiags:verbose

javac -cp ".:${lib_path}*"			-d "$classes_path" "$java_path/helpers_J/MyCast.java"			-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*:${classes_path}" 	-d "$classes_path" "$java_path/helpers_J/Formulaire.java"		-Xlint -Xdiags:verbose
javac -cp ".:${lib_path}*:${classes_path}" 	-d "$classes_path" "$java_path/etu1933/framework/servlet/FrontServlet.java"	-Xlint:-serial 


# --------------------------- MIS EN .JAR DES CLASSES ---------------------------------
jar cf JFramework.jar -C "$classes_path" .
# --------------------------- MIS EN .JAR DES CLASSES ---------------------------------


# --------------------------- COPIE DU .JAR VERS LE PROJET TEMPORAIRE ---------------------------
# Deplacer vers le librairies
mv JFramework.jar "$lib_path"
rm -R "$classes_path"
# --------------------------- COPIE DU .JAR VERS LE PROJET TEMPORAIRE ---------------------------





# -------------------------------------------------- COMPILATION DU PROJET TEMPORAIRE -----------------------------------
java_path="/home/johan/Documents/Programmation/Naina/Framework/Temporaire/java"

# Dossier des classes temporaire
mkdir "$classes_path"

# Compiler les fichiers .java vers la "classes_path"   /  *: specifie tout les .jar present et /: tout les .classe deja compile
find "$java_path" -name "*.java" -print0 | xargs -0 javac -cp ".:${lib_path}*" -d "$classes_path" -Xlint:-serial -Xlint -Xdiags:verbose
# -------------------------------------------------- COMPILATION DU PROJET TEMPORAIRE -----------------------------------


# --------------------------- MISE EN .WAR ---------------------------
>>>>>>> Stashed changes
# Chemin de sortie pour le fichier WAR
WAR_OUTPUT_PATH="../JTeste.war"

# Exporter le projet en tant que WAR
"$NETBEANS_HOME"/bin/ant -f "$PROJECT_PATH/build.xml" clean dist -Ddist.dir="$PROJECT_PATH/dist" -Ddist.war="$WAR_OUTPUT_PATH" -Dproject.name="$PROJECT_NAME"

# Copie de fichier war vers tomcat9
<<<<<<< Updated upstream
sudo mv JTeste.war /var/lib/tomcat9/webapps/
#rm JTeste.war
=======
sudo mv "$WAR_OUTPUT_PATH" /var/lib/tomcat9/webapps/
# --------------------------- MISE EN .WAR ---------------------------
>>>>>>> Stashed changes


