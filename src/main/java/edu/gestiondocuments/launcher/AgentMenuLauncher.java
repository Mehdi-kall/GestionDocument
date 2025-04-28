package edu.gestiondocuments.launcher;

import edu.gestiondocuments.tests.AgentMenu;

/**
 * Launcher class for AgentMenu that doesn't require module path arguments.
 * This class can be run directly from the IDE without additional VM arguments.
 */
public class AgentMenuLauncher {
    public static void main(String[] args) {
        // Simply launch the JavaFX application
        AgentMenu.main(args);
    }
}
