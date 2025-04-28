@echo off
REM Script pour exécuter l'application JavaFX

REM Définir le chemin vers le JDK
set JAVA_HOME=C:\Users\mehdi\.jdks\jbr-17.0.12

REM Définir le chemin vers les modules JavaFX
set PATH_TO_FX=C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib

REM Vérifier si les modules JavaFX sont disponibles
if not exist "%PATH_TO_FX%\javafx.graphics.jar" (
    echo ERREUR: Les modules JavaFX ne sont pas trouvés à l'emplacement spécifié: %PATH_TO_FX%
    echo Veuillez vérifier le chemin des modules JavaFX dans ce script.
    pause
    exit /b 1
)

echo.
echo Exécution de l'application...
echo.

REM Exécuter l'application avec Java en spécifiant explicitement le chemin des modules JavaFX
"%JAVA_HOME%\bin\java" ^
    --module-path "%PATH_TO_FX%" ^
    --add-modules=javafx.controls,javafx.fxml,javafx.graphics ^
    -cp "target\classes;target\dependency\*" ^
    edu.gestiondocuments.tests.AgentMenu

pause
