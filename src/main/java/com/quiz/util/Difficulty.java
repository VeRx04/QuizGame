package com.quiz.util;

/**
 * Enum zur Repräsentation der Schwierigkeitsstufen im Quiz.
 */
public enum Difficulty {
    LEICHT("Leicht"),
    MITTEL("Mittel"),
    SCHWER("Schwer");

    private final String item;

    /**
     * Konstruktor für die Schwierigkeitsstufe.
     * @param item Die String-Repräsentation der Schwierigkeitsstufe.
     */
    Difficulty(String item) {
        this.item = item;
    }

    /**
     * Gibt die String-Repräsentation der Schwierigkeitsstufe zurück.
     * @return Der Name der Schwierigkeitsstufe.
     */
    @Override
    public String toString() {
        return item;
    }
}
