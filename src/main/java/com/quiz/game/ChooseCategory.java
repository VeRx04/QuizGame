package com.quiz.game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.quiz.main.Frame;
import com.quiz.util.*;

/**
 * Panel zur Auswahl einer Kategorie für das Quiz.
 */
public class ChooseCategory extends JPanel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 2658164544626941757L;
	
	private final Frame frame;

    /**
     * Erstellt das Panel zur Kategorieauswahl.
     * @param frame Hauptfenster des Spiels
     */
    public ChooseCategory(Frame frame) {
        this.frame = frame;
        configurePanel();
        addCategoryButtons();
    }

    /**
     * Konfiguriert das Panel.
     */
    private void configurePanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Config.PANEL_BG_COLOR);
    }

    /**
     * Fügt die Kategorie-Knöpfe hinzu.
     */
    private void addCategoryButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 30, 30));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(150, 50, 150, 50));
        buttonPanel.setBackground(Config.PANEL_BG_COLOR);

        for (Category category : Config.CATEGORIES) {
            String formattedText = TextFormatter.formatText(category.toString());
            JButton button = Component.createButton(formattedText, new Rectangle(0, 0, 0, 0));
            button.addActionListener(e -> {
                UserSession.setCategory(category);
                frame.switchPanel(new ChooseDifficulty(frame));
            });
            buttonPanel.add(button);
        }

        this.add(buttonPanel);
    }
}
