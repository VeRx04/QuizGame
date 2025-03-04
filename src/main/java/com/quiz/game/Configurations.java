package com.quiz.game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.quiz.component.ModifiedComboBox;
import com.quiz.main.Frame;
import com.quiz.util.*;

/**
 * Panel zur Konfiguration weiterer Quiz-Einstellungen: Fragenanzahl & Zeitlimit.
 */
public class Configurations extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2814568956062556013L;
	
	private final Frame frame;
    private ModifiedComboBox<Integer> questionCountSelector;
    private ModifiedComboBox<Integer> timeLimitSelector;

    /**
     * Erstellt das Panel zur Auswahl der Konfiguration.
     * @param frame Hauptfenster des Spiels
     */
    public Configurations(Frame frame) {
        this.frame = frame;
        setLayout(new GridBagLayout());
        setBackground(Config.PANEL_BG_COLOR);
        addComponents();
    }

    /**
     * Fügt UI-Komponenten zum Panel hinzu.
     */
    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 0, 5, 0);

        createLabels(gbc);
        createDropdowns(gbc);
        createButtons(gbc);
    }

    /**
     * Erstellt und fügt die Labels zum Panel hinzu.
     */
    private void createLabels(GridBagConstraints gbc) {
        JLabel questionLabel = Component.createLabel("Anzahl der Fragen:", new Rectangle(0, 0, 0, 0), Config.COMPONENT_FONT);
        gbc.gridy++;
        gbc.insets = new Insets(0, 0, 10, 0);
        this.add(questionLabel, gbc);

        JLabel timeLabel = Component.createLabel("Zeitlimit in Minuten:", new Rectangle(0, 0, 0, 0), Config.COMPONENT_FONT);
        gbc.gridy += 2;
        this.add(timeLabel, gbc);
    }

    /**
     * Erstellt und fügt die Auswahlfelder für Fragenanzahl & Zeitlimit hinzu.
     */
    private void createDropdowns(GridBagConstraints gbc) {
        Integer[] questionOptions = {5, 10, 15, 20};
        Integer[] timeOptions = {5, 10, 20, 30};

        questionCountSelector = new ModifiedComboBox<>(questionOptions);
        questionCountSelector.setPreferredSize(new Dimension(220, 50));
        Effect.applyHoverEffects(questionCountSelector);

        timeLimitSelector = new ModifiedComboBox<>(timeOptions);
        timeLimitSelector.setPreferredSize(new Dimension(220, 50));
        Effect.applyHoverEffects(timeLimitSelector);

        gbc.gridy--;
        gbc.insets = new Insets(0, 0, 25, 0);
        this.add(questionCountSelector, gbc);

        gbc.gridy += 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(timeLimitSelector, gbc);
    }

    /**
     * Erstellt und fügt den Weiter-Knopf zum Panel hinzu.
     */
    private void createButtons(GridBagConstraints gbc) {
        JButton continueButton = Component.createButton("Weiter", new Rectangle(0, 0, 0, 0));
        continueButton.setPreferredSize(new Dimension(220, 60));
        continueButton.addActionListener(e -> handleContinue());

        gbc.gridy += 2;
        gbc.fill = GridBagConstraints.NONE;
        continueButton.setPreferredSize(new Dimension(150, 50));
        gbc.insets = new Insets(50, 0, 0, 0);
        this.add(continueButton, gbc);
    }

    /**
     * Speichert die Konfiguration und startet das Spiel.
     */
    private void handleContinue() {
        UserSession.setQuestionCount((Integer) questionCountSelector.getSelectedItem());
        UserSession.setTimeLimit((Integer) timeLimitSelector.getSelectedItem() * 60);
        frame.switchPanel(new GamePanel(frame));
        frame.stopMusic();
        frame.playMusic(Resources.GAME_MUSIC);
    }
}
