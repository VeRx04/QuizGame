package com.quiz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hilfsklasse zur Formatierung von Texten für die UI.
 */
public class TextFormatter {
    private static boolean isFullscreen = false; // Standardmäßig im Fenstermodus
    private static final Map<String, Integer[]> HYPHEN_POSITIONS = new HashMap<>();

    static {
        HYPHEN_POSITIONS.put("Softwareentwicklung", new Integer[]{8});
        HYPHEN_POSITIONS.put("Allgemeinwissen", new Integer[]{9});
        HYPHEN_POSITIONS.put("Betriebswirtschaftslehre", new Integer[]{8, 19});
        HYPHEN_POSITIONS.put("Internetgeschwindigkeit", new Integer[]{8});
        HYPHEN_POSITIONS.put("Informationstechnologie", new Integer[]{12});
        HYPHEN_POSITIONS.put("Programmiersprache", new Integer[]{11});
    }

    /**
     * Setzt den Bildschirmmodus (Vollbild oder Fenstermodus).
     * @param fullscreen true = Vollbild, false = Fenstermodus
     */
    public static void setFullscreenMode(boolean fullscreen) {
        isFullscreen = fullscreen;
    }

    /**
     * Formatiert den Text für HTML-Darstellung mit Zeilenumbrüchen.
     * Entfernt Trennzeichen, wenn im Vollbildmodus.
     * @param text Der zu formatierende Text.
     * @return Der HTML-formatierte Text.
     */   
    public static String formatText(String text) {
        if (isFullscreen) {
            return wrapHtml(text.replace("-<br>", "")); // Keine Trennungen im Vollbildmodus
        }

        for (Map.Entry<String, Integer[]> entry : HYPHEN_POSITIONS.entrySet()) {
            String word = entry.getKey();
            Integer[] positions = entry.getValue();

            Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(text);
            
            while (matcher.find()) {
                String formattedWord = word;
                StringBuilder sb = new StringBuilder(word);
                for (int i = positions.length - 1; i >= 0; i--) {
                    sb.insert(positions[i], "-<br>");
                }
                formattedWord = sb.toString();
                text = text.replace(matcher.group(), formattedWord); 
            }
        }

        return wrapHtml(text); 
    }

    /**
     * Verpackt Text in HTML für bessere Darstellung.
     * @param text Der einzubettende Text.
     * @return Der HTML-formatierte Text.
     */
    private static String wrapHtml(String text) {
        return "<html><div style='text-align: center;'>" + text + "</div></html>";
    }
}
