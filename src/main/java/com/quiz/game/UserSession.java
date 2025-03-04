package com.quiz.game;

import com.quiz.util.*;

/**
 * Verwaltet die aktuelle Benutzersitzung, einschließlich Benutzername,
 * Kategorie, Schwierigkeitsgrad, Anzahl der Fragen und Zeitlimit.
 */
public class UserSession {
    private static String username;
    private static Category category;
    private static Difficulty difficulty;
    private static int questionCount;
    private static int timeLimit;

    /**
     * Setzt den Benutzernamen für die Sitzung.
     * @param name Benutzername
     */
    public static void setUsername(String name) {
        username = name;
    }

    /**
     * Gibt den aktuellen Benutzernamen zurück.
     * @return Benutzername
     */
    public static String getUsername() {
        return username;
    }

    /**
     * Setzt die ausgewählte Kategorie für die Sitzung.
     * @param cat Kategorie
     */
    public static void setCategory(Category cat) {
        category = cat;
    }

    /**
     * Gibt die aktuelle Kategorie zurück.
     * @return Kategorie
     */
    public static Category getCategory() {
        return category;
    }

    /**
     * Setzt die ausgewählte Schwierigkeit für die Sitzung.
     * @param diff Schwierigkeitsgrad
     */
    public static void setDifficulty(Difficulty diff) {
        difficulty = diff;
    }

    /**
     * Gibt die aktuelle Schwierigkeit zurück.
     * @return Schwierigkeitsgrad
     */
    public static Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Setzt die Anzahl der Fragen für die Sitzung.
     * @param count Anzahl der Fragen
     */
    public static void setQuestionCount(int count) {
        questionCount = count;
    }

    /**
     * Gibt die Anzahl der Fragen zurück.
     * @return Anzahl der Fragen
     */
    public static int getQuestionCount() {
        return questionCount;
    }

    /**
     * Setzt das Zeitlimit für das Spiel in Sekunden.
     * @param time Zeitlimit in Sekunden
     */
    public static void setTimeLimit(int time) {
        timeLimit = time;
    }

    /**
     * Gibt das Zeitlimit für die Sitzung zurück.
     * @return Zeitlimit in Sekunden
     */
    public static int getTimeLimit() {
        return timeLimit;
    }
}
