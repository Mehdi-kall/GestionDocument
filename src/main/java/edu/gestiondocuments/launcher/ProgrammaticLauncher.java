package edu.gestiondocuments.launcher;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * This launcher attempts to set the JavaFX module path programmatically.
 * Note: This is an experimental approach and may not work in all environments.
 */
public class ProgrammaticLauncher {
    public static void main(String[] args) {
        try {
            // Path to JavaFX SDK
            String javaFxPath = "C:\\Users\\mehdi\\Downloads\\openjfx-17.0.14_windows-x64_bin-sdk\\javafx-sdk-17.0.14\\lib";
            
            // Verify the path exists
            File javaFxDir = new File(javaFxPath);
            if (!javaFxDir.exists() || !javaFxDir.isDirectory()) {
                System.err.println("JavaFX SDK not found at: " + javaFxPath);
                System.exit(1);
            }
            
            // Set the module path system property
            System.setProperty("jdk.module.path", javaFxPath);
            
            // Add JavaFX modules
            System.setProperty("jdk.module.addmods", "javafx.controls,javafx.fxml,javafx.graphics");
            
            // Launch the actual application
            System.out.println("Launching AgentMenu application...");
            edu.gestiondocuments.tests.AgentMenu.main(args);
            
        } catch (Exception e) {
            System.err.println("Error launching application: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
