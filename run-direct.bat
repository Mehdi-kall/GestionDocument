@echo off
setlocal

echo ===================================================
echo Lancement de l'application GestionDocuments avec Java
echo ===================================================

REM Chemin vers le JDK
set JAVA_HOME=C:\Users\mehdi\.jdks\jbr-17.0.12

REM Chemin vers les modules JavaFX
set PATH_TO_FX=C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib

REM Vérifier si Java est disponible
if not exist "%JAVA_HOME%\bin\java.exe" (
    echo ERREUR: Java n'est pas trouvé à l'emplacement spécifié: %JAVA_HOME%
    echo Veuillez vérifier le chemin du JDK dans ce script.
    pause
    exit /b 1
)

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
    edu.gestiondocuments.tests.Agent

if %ERRORLEVEL% neq 0 (
    echo.
    echo ERREUR: L'exécution a échoué avec le code %ERRORLEVEL%.
    echo Vérifiez les messages d'erreur ci-dessus.
) else (
    echo.
    echo Application terminée avec succès.
)

pause
endlocal
