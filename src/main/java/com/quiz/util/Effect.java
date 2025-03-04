package com.quiz.util;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JComponent;

/**
 * Hilfsklasse zur Anwendung von Effekten auf UI-Komponenten.
 */
public class Effect {

    /**
     * Konfiguriert die visuelle Darstellung einer UI-Komponente.
     * @param component Die zu konfigurierende Komponente.
     * @param bounds    Die Position und Größe der Komponente.
     * @param font      Die Schriftart für die Komponente.
     */
    public static void configureComponent(JComponent component, Rectangle bounds, Font font) {
        component.setFont(font);
        component.setForeground(Config.TEXT_COLOR);
        component.setBackground(Config.COMPONENT_BG_COLOR);
        component.setBounds(bounds);
    }

    /**
     * Fügt Hover-Effekte zu einer UI-Komponente hinzu.
     * Die Farbe der Komponente ändert sich bei Hover oder Klick.
     * @param component Die zu modifizierende UI-Komponente.
     */
    public static void applyHoverEffects(JComponent component) {
        component.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                component.setBackground(Config.COMPONENT_BG_COLOR.brighter());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                component.setBackground(Config.COMPONENT_BG_COLOR);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                component.setBackground(Config.COMPONENT_BG_COLOR.darker());
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                component.setBackground(Config.COMPONENT_BG_COLOR);
            }
        });
    }
}
