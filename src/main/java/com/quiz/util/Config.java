package com.quiz.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Enthält globale Konfigurationswerte für das Quiz-Spiel.
 */
public class Config {
    // Fenster-Konfigurationen
    public static final String WINDOW_TITLE = "Quiz";
    public static final Dimension WINDOW_SIZE = new Dimension(600, 800);

    // Farben für UI-Elemente
    public static final Color PANEL_BG_COLOR = new Color(55, 22, 86);
    public static final Color COMPONENT_BG_COLOR = new Color(72, 29, 112);
    public static final Color CARD_BG_COLOR = new Color(90, 50, 100);
    public static final Color BORDER_COLOR = new Color(155, 122, 166);
    public static final Color TEXT_COLOR = new Color(255, 255, 255);

    // Schriftarten
    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 26);
    public static final Font COMPONENT_FONT = new Font("SansSerif", Font.BOLD, 22);
    public static final Font CARD_FONT = new Font("SansSerif", Font.BOLD, 24);

    // Berechnete Fontgrößen
    public static final int TITLE_FONT_SIZE = (int) (TITLE_FONT.getSize() * 0.75);
    public static final int COMPONENT_FONT_SIZE = (int) (COMPONENT_FONT.getSize() * 0.75);
    public static final int CARD_FONT_SIZE = (int) (CARD_FONT.getSize() * 0.75);

    // UI-Rundungen
    public static final int BORDER_RADIUS = 30;

    // Spielkonfigurationen
    public static final Category[] CATEGORIES = Category.values();
    public static final Difficulty[] DIFFICULTIES = Difficulty.values();
}
