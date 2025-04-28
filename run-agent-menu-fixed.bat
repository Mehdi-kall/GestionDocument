@echo off
REM Script pour exécuter l'application JavaFX AgentMenu avec les bons arguments

REM Définir le chemin vers le JDK
set JAVA_HOME=C:\Users\mehdi\.jdks\jbr-17.0.12

REM Définir le chemin vers les modules JavaFX
set PATH_TO_FX=C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib

echo.
echo Exécution de l'application AgentMenu...
echo.

REM Exécuter l'application avec Java en spécifiant les arguments JVM correctement
"%JAVA_HOME%\bin\java" ^
    --module-path "%PATH_TO_FX%" ^
    --add-modules=javafx.controls,javafx.fxml,javafx.graphics ^
    -cp "target\classes;C:\Users\mehdi\.m2\repository\com\mysql\mysql-connector-j\8.0.33\mysql-connector-j-8.0.33.jar;C:\Users\mehdi\.m2\repository\com\google\protobuf\protobuf-java\3.21.9\protobuf-java-3.21.9.jar" ^
    edu.gestiondocuments.tests.AgentMenu

pause
