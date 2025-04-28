@echo off
setlocal

echo ===================================================
echo Lancement de l'application GestionDocuments avec Maven
echo ===================================================

REM Vérifier si Maven est installé
where mvn >nul 2>nul
if %ERRORLEVEL% neq 0 (
    echo ERREUR: Maven n'est pas installé ou n'est pas dans le PATH.
    echo Veuillez installer Maven et l'ajouter au PATH.
    pause
    exit /b 1
)

REM Chemin vers les modules JavaFX - Ajustez ce chemin selon votre installation
set PATH_TO_FX=C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib

echo.
echo Compilation et exécution du projet...
echo.

REM Exécuter l'application avec Maven en spécifiant explicitement le chemin des modules JavaFX
mvn clean javafx:run -Djavafx.sdk=%PATH_TO_FX% -Djavafx.mainClass=edu.gestiondocuments.tests.AgentMenu

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
