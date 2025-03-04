package com.quiz.event;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.quiz.main.Frame;

/**
 * Behandelt Tastatureingaben für das Quiz-Spiel.
 * Aktuell wird nur die Umschaltung zwischen Vollbild- und Fenstermodus unterstützt.
 */
public class KeyHandler extends KeyAdapter {
    private final Frame frame;

    /**
     * Erstellt einen KeyHandler für das Hauptfenster.
     * @param frame Das Hauptfenster des Spiels
     */
    public KeyHandler(Frame frame) {
        this.frame = frame;
        frame.setFocusable(true);
    }

    /**
     * Wird aufgerufen, wenn eine Taste gedrückt wird.
     * Umschaltet den Bildschirmmodus bei Drücken von F11.
     * @param e Das KeyEvent mit der gedrückten Taste
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_F11) {
            frame.toggleFullscreen();
        }
    }
}
