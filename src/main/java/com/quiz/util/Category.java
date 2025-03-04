package com.quiz.util;

/**
 * Enum zur Repräsentation der Kategorien im Quiz.
 */
public enum Category {
    SAE("Softwareentwicklung"),
    BASICS("Allgemeinwissen"),
    BWL("Betriebswirtschaftslehre"),
    IT_SYSTEMS("IT-Systeme"),
    DB("Datenbanken"),
    SCI("Wissenschaft");

    private final String item;

    /**
     * Konstruktor für die Kategorie.
     * @param item Die String-Repräsentation der Kategorie.
     */
    Category(String item) {
        this.item = item;
    }

    /**
     * Gibt die String-Repräsentation der Kategorie zurück.
     * @return Der Name der Kategorie.
     */
    @Override
    public String toString() {
        return item;
    }
}
