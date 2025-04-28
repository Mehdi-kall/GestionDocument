@echo off
echo Lancement de l'application GestionDocuments...

REM Chemin vers le JDK - Utilisez votre JDK installé
set JAVA_HOME=C:\Users\mehdi\.jdks\jbr-17.0.12

REM Chemin vers les modules JavaFX - Ajustez ce chemin selon votre installation
set PATH_TO_FX=C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib

REM Lancement de l'application
"%JAVA_HOME%\bin\java" --module-path %PATH_TO_FX% --add-modules=javafx.controls,javafx.fxml,javafx.graphics -cp target\classes edu.gestiondocuments.tests.Agent

echo Application terminée.
pause
