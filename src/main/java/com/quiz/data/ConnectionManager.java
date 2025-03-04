package com.quiz.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Verwaltet die Verbindung zur Datenbank.
 */
public class ConnectionManager {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
    private static final String URL = "jdbc:mysql://localhost:3306/quiz";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public ConnectionManager() {
        startConnection();
    }

    /**
     * Initialisiert die Datenbankverbindung.
     */
    private void startConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            logger.info("Datenbankverbindung erfolgreich hergestellt.");
        } catch (ClassNotFoundException e) {
            logger.error("JDBC-Treiber nicht gefunden.", e);
        } catch (SQLException e) {
            logger.error("Fehler beim Herstellen der Verbindung zur Datenbank.", e);
        }
    }

    /**
     * Gibt die aktive Datenbankverbindung zurück.
     * @return Connection-Objekt oder null bei Verbindungsproblemen.
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Schließt die Datenbankverbindung.
     */
    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                logger.info("Datenbankverbindung geschlossen.");
            } catch (SQLException e) {
                logger.error("Fehler beim Schließen der Verbindung.", e);
            }
        }
    }
}
