# Spécifiez le chemin du projet NetBeans
java_path="/home/johan/Documents/Programmation/Naina/Framework/JFramework/src/java"
# Chemin vers le dossier temporaire projet
PROJECT_PATH="/home/johan/Documents/Programmation/Naina/Framework/Temporaire"

# Dossier des classes temporaire
classes_path="./classes"
mkdir "$classes_path"
# Dossier des librairies temporaire
lib_path="./librairies/"
mkdir "$lib_path"

#Copier les librairairies utils
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-beanutils-1.9.4.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-collections-3.2.2.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/commons-beanutils-1.9.4/commons-logging-1.2.jar "$lib_path" 
cp /home/johan/Documents/Programmation/Java/Librairie_JAVA/servlet-api.jar "$lib_path" 

# Compiler les fichiers .java vers la "classes_path"   /  *: specifie tout les .jar present et /: tout les .classe deja compile
javac -d "$classes_path" "$java_path/annotation_J/Url.java"
javac -cp ".:${lib_path}*" -d "$classes_path" "$java_path/etu1933/framework/view/ModelView.java"
javac -d "$classes_path" "$java_path/etu1933/framework/Mapping.java"
javac -cp ".:${classes_path}" -d "$classes_path" "$java_path/helpers_J/Init.java"
javac -cp ".:${lib_path}*:${classes_path}" -d "$classes_path" "$java_path/helpers_J/Formulaire.java"
javac -cp ".:${lib_path}*:${classes_path}" -d "$classes_path" "$java_path/etu1933/framework/servlet/FrontServlet.java"

# MIS EN .JAR DES CLASSES
jar cf JFramework.jar -C "$classes_path" .
# Effacer les dossiers temporaires
rm -R "$classes_path"
rm -R "$lib_path"
# MIS EN .JAR DES CLASSES


# COPIE DU .JAR VERS LE PROJET TEMPORAIRE
# Deplacer vers le librairies
mv JFramework.jar "$PROJECT_PATH/lib"
# COPIE DU .JAR VERS LE PROJET TEMPORAIRE


# MISE EN .WAR
# Chemin de sortie pour le fichier WAR
WAR_OUTPUT_PATH="JTeste.war"
# Exporter le projet en tant que WAR
jar cvf "$WAR_OUTPUT_PATH" -C "$PROJECT_PATH" .
# Copie de fichier war vers tomcat9
sudo mv "$WAR_OUTPUT_PATH" /var/lib/tomcat9/webapps/
# MISE EN .WAR


