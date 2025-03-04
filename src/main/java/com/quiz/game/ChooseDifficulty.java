package com.quiz.game;

import javax.swing.*;
import com.quiz.main.Frame;
import com.quiz.util.*;
import com.quiz.util.Component;

import java.awt.*;

/**
 * Panel zur Auswahl des Schwierigkeitsgrads für das Quiz.
 */
public class ChooseDifficulty extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8285570174828803540L;
	
	private final Frame frame;

    /**
     * Erstellt das Panel zur Auswahl des Schwierigkeitsgrads.
     * @param frame Hauptfenster des Spiels
     */
    public ChooseDifficulty(Frame frame) {
        this.frame = frame;
        configurePanel();
        addDifficultyButtons();
    }

    /**
     * Konfiguriert das Panel.
     */
    private void configurePanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Config.PANEL_BG_COLOR);
    }

    /**
     * Fügt die Schwierigkeitsgrad-Knöpfe hinzu.
     */
    private void addDifficultyButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(150, 50, 150, 50));
        buttonPanel.setBackground(Config.PANEL_BG_COLOR);

        for (Difficulty difficulty : Config.DIFFICULTIES) {
            String formattedText = TextFormatter.formatText(difficulty.toString());
            JButton button = Component.createButton(formattedText, new Rectangle(0, 0, 0, 0));
            button.addActionListener(e -> {
                UserSession.setDifficulty(difficulty);
                frame.switchPanel(new Configurations(frame));
            });
            buttonPanel.add(button);
        }

        this.add(buttonPanel);
    }
}
