@echo off
REM Script pour exécuter l'application JavaFX avec le plugin Maven JavaFX

echo.
echo Compilation et exécution de l'application avec le plugin Maven JavaFX...
echo.

REM Nettoyer et compiler le projet
call mvn clean compile

REM Exécuter l'application avec le plugin JavaFX Maven
call mvn javafx:run -Djavafx.mainClass=edu.gestiondocuments.tests.SimpleJavaFXApp

pause
