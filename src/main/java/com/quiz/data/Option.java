package com.quiz.data;

/**
 * Repräsentiert eine einzelne Antwortmöglichkeit für eine Quizfrage.
 */
public class Option {
    private final int id;
    private final String optionText;
    private final boolean isCorrect;

    /**
     * Erstellt eine Antwortmöglichkeit mit ID, Text und Korrektheitsstatus.
     * @param id          Die ID der Antwortmöglichkeit
     * @param optionText  Der Text der Antwortmöglichkeit
     * @param isCorrect   Gibt an, ob die Antwort korrekt ist
     */
    public Option(int id, String optionText, boolean isCorrect) {
        this.id = id;
        this.optionText = optionText;
        this.isCorrect = isCorrect;
    }

    /**
     * Gibt die ID der Antwortoption zurück.
     * @return Die ID der Antwortoption
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt den Text der Antwortoption zurück.
     * @return Der Text der Antwortoption
     */
    public String getOptionText() {
        return optionText;
    }

    /**
     * Gibt zurück, ob die Antwort korrekt ist.
     * @return true, wenn die Antwort richtig ist, ansonsten false
     */
    public boolean isCorrect() {
        return isCorrect;
    }
}
