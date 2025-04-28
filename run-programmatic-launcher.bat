@echo off
REM Script pour exécuter l'application JavaFX via le lanceur programmatique

REM Définir le chemin vers le JDK
set JAVA_HOME=C:\Users\mehdi\.jdks\jbr-17.0.12

echo.
echo Exécution de l'application via le lanceur programmatique...
echo.

REM Exécuter le lanceur programmatique
"%JAVA_HOME%\bin\java" ^
    -cp "target\classes;C:\Users\mehdi\.m2\repository\com\mysql\mysql-connector-j\8.0.33\mysql-connector-j-8.0.33.jar;C:\Users\mehdi\.m2\repository\com\google\protobuf\protobuf-java\3.21.9\protobuf-java-3.21.9.jar;C:\Users\mehdi\.m2\repository\org\openjfx\javafx-controls\17.0.14\javafx-controls-17.0.14.jar;C:\Users\mehdi\.m2\repository\org\openjfx\javafx-controls\17.0.14\javafx-controls-17.0.14-win.jar;C:\Users\mehdi\.m2\repository\org\openjfx\javafx-fxml\17.0.14\javafx-fxml-17.0.14.jar;C:\Users\mehdi\.m2\repository\org\openjfx\javafx-fxml\17.0.14\javafx-fxml-17.0.14-win.jar;C:\Users\mehdi\.m2\repository\org\openjfx\javafx-graphics\17.0.14\javafx-graphics-17.0.14.jar;C:\Users\mehdi\.m2\repository\org\openjfx\javafx-graphics\17.0.14\javafx-graphics-17.0.14-win.jar;C:\Users\mehdi\.m2\repository\org\openjfx\javafx-base\17.0.14\javafx-base-17.0.14.jar;C:\Users\mehdi\.m2\repository\org\openjfx\javafx-base\17.0.14\javafx-base-17.0.14-win.jar;C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib\javafx-swt.jar;C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib\javafx.web.jar;C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib\javafx.base.jar;C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib\javafx.fxml.jar;C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib\javafx.media.jar;C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib\javafx.swing.jar;C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib\javafx.controls.jar;C:\Users\mehdi\Downloads\openjfx-17.0.14_windows-x64_bin-sdk\javafx-sdk-17.0.14\lib\javafx.graphics.jar" ^
    edu.gestiondocuments.launcher.ProgrammaticLauncher

pause
