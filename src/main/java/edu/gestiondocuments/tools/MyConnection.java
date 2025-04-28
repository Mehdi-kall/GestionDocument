package edu.gestiondocuments.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static MyConnection instance = null;
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost:3306/projetjava";
    private final String USER = "root";
    private final String PASSWORD = "";

    // Constructeur privé
    private MyConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion établie!");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erreur de connexion: " + e.getMessage());
        }
    }

    // Méthode pour obtenir l'instance unique
    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }

    // Méthode pour obtenir la connexion
    public Connection getConnection() {
        return connection;
    }
}