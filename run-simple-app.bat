@echo off
REM Script pour exécuter l'application JavaFX simple

REM Définir le chemin vers le JDK
set JAVA_HOME=C:\Users\mehdi\.jdks\jbr-17.0.12

REM Définir le chemin vers les modules JavaFX
set PATH_TO_FX=C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib

echo.
echo Exécution de l'application JavaFX simple...
echo.

REM Exécuter l'application avec Java en spécifiant les arguments JVM correctement
"%JAVA_HOME%\bin\java" ^
    --module-path "%PATH_TO_FX%" ^
    --add-modules=javafx.controls ^
    -cp "target\classes" ^
    edu.gestiondocuments.tests.SimpleJavaFXApp

pause
