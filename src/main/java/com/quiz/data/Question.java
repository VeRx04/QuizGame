package com.quiz.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Repräsentiert eine einzelne Quizfrage mit Kategorie, Schwierigkeitsgrad und Antwortoptionen.
 */
public class Question {
    private final int id;
    private final String questionText;
    private final String category;
    private final String difficulty;
    private final List<Option> options;

    /**
     * Erstellt eine neue Frage mit ID, Text, Kategorie und Schwierigkeitsgrad.
     * @param id           Die ID der Frage
     * @param questionText Der Text der Frage
     * @param category     Die Kategorie der Frage
     * @param difficulty   Der Schwierigkeitsgrad der Frage
     */
    public Question(int id, String questionText, String category, String difficulty) {
        this.id = id;
        this.questionText = questionText;
        this.category = category;
        this.difficulty = difficulty;
        this.options = new ArrayList<>();
    }

    /**
     * Gibt die ID der Frage zurück.
     * @return Die ID der Frage
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt den Fragetext zurück.
     * @return Der Fragetext
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Gibt die Kategorie der Frage zurück.
     * @return Die Kategorie der Frage
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gibt den Schwierigkeitsgrad der Frage zurück.
     * @return Der Schwierigkeitsgrad der Frage
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Gibt die Liste der Antwortoptionen zurück.
     * @return Eine Liste von Antwortmöglichkeiten
     */
    public List<Option> getOptions() {
        return options;
    }

    /**
     * Fügt eine neue Antwortoption zur Frage hinzu.
     * @param Die hinzuzufügende Antwortmöglichkeit
     */
    public void addOption(Option option) {
        options.add(option);
    }
}
