#!/bin/bash

# Spécifiez le chemin du projet NetBeans
java_path="/home/johan/Documents/Programmation/Naina/Framework/JFramework/build/web/WEB-INF/classes"

# Chemin vers le dossier temporaire projet
PROJECT_PATH="/home/johan/Documents/Programmation/Naina/Framework/Temporaire"

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
# Chemin de sortie pour le fichier WAR
WAR_OUTPUT_PATH="../JTeste.war"

# Exporter le projet en tant que WAR
"$NETBEANS_HOME"/bin/ant -f "$PROJECT_PATH/build.xml" clean dist -Ddist.dir="$PROJECT_PATH/dist" -Ddist.war="$WAR_OUTPUT_PATH" -Dproject.name="$PROJECT_NAME"

# Copie de fichier war vers tomcat9
sudo mv JTeste.war /var/lib/tomcat9/webapps/
#rm JTeste.war


