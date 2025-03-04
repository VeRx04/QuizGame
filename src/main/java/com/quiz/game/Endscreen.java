package com.quiz.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.quiz.data.Queries;
import com.quiz.main.Frame;
import com.quiz.util.*;

/**
 * Panel für den Endbildschirm nach Spielende.
 */
public class Endscreen extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9017482399539414956L;
	
	private final Frame frame;
    private final int score;

    /**
     * Erstellt den Endbildschirm mit Punktestand und Leaderboard.
     * @param frame Hauptfenster des Spiels
     * @param score Erzielte Punkte des Spielers
     */
    public Endscreen(Frame frame, int score) {
        this.frame = frame;
        this.score = score;
        setLayout(new BorderLayout());
        setBackground(Config.PANEL_BG_COLOR);
        addComponents();
    }

    /**
     * Fügt UI-Komponenten zum Panel hinzu.
     */
    private void addComponents() {
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel titlePanel = createTitlePanel();
        this.add(titlePanel, BorderLayout.NORTH);

        JPanel leaderboardPanel = createLeaderboardPanel();
        this.add(leaderboardPanel, BorderLayout.CENTER);

        JPanel actionPanel = createActionPanel();
        this.add(actionPanel, BorderLayout.SOUTH);
    }

    /**
     * Erstellt das Panel mit Abschlussnachricht und Punktestand.
     * @return Panel mit Abschlussnachricht und Punktestand
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Config.PANEL_BG_COLOR);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel gameOverLabel = Component.createLabel("Game Over", new Rectangle(0, 0, 200, 50), Config.TITLE_FONT);
        gameOverLabel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel scoreLabel = Component.createLabel("Dein Score: " + score, new Rectangle(0, 0, 200, 30), Config.COMPONENT_FONT);
        scoreLabel.setAlignmentX(CENTER_ALIGNMENT);

        titlePanel.add(gameOverLabel);
        titlePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        titlePanel.add(scoreLabel);

        return titlePanel;
    }

    /**
     * Erstellt das Leaderboard-Panel mit den Top-Spielern.
     * @param leaderboardPanel Panel mit der Bestenliste
     * @return Panel der Bestenliste
     */
    private JPanel createLeaderboardPanel() {
        JPanel leaderboardPanel = new JPanel();
        leaderboardPanel.setBackground(Config.PANEL_BG_COLOR);
        leaderboardPanel.setLayout(new BoxLayout(leaderboardPanel, BoxLayout.Y_AXIS));

        Queries queries = new Queries();
        List<String> leaderboard = queries.getLeaderboard();

        JLabel leaderboardTitle = Component.createLabel("Leaderboard", new Rectangle(0, 0, 200, 50), Config.TITLE_FONT);
        leaderboardTitle.setAlignmentX(CENTER_ALIGNMENT);
        leaderboardPanel.add(leaderboardTitle);
        leaderboardPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        for (String entry : leaderboard) {
            JLabel entryLabel = Component.createLabel(entry, new Rectangle(0, 0, 200, 30), Config.COMPONENT_FONT);
            entryLabel.setAlignmentX(CENTER_ALIGNMENT);
            leaderboardPanel.add(entryLabel);
        }

        return leaderboardPanel;
    }

    /**
     * Erstellt das Interaktions-Panel mit Möglichkeiten
     * vom erneutem Spielen & Beenden des Quiz-Spiels.
     * @param actionPanel Panel mit dem Wiederholen- & Beenden-Knopf
     * @return Panel zum erneutem Spielen oder Beenden des Quiz-Spiels
     */
    private JPanel createActionPanel() {
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(Config.PANEL_BG_COLOR);

        JButton playAgainButton = Component.createButton("Erneut spielen", new Rectangle(0, 0, 200, 60));
        playAgainButton.addActionListener(e -> restartGame());
        playAgainButton.setPreferredSize(new Dimension(200, 70));

        JButton quitButton = Component.createButton("Beenden", new Rectangle(0, 0, 200, 60));
        quitButton.addActionListener(e -> quitGame());
        quitButton.setPreferredSize(new Dimension(200, 70));

        actionPanel.setLayout(new GridLayout(1, 2, 20, 20));
        actionPanel.add(playAgainButton);
        actionPanel.add(quitButton);

        return actionPanel;
    }

    /**
     * Neustarten des Quiz-Spiels.
     */
    private void restartGame() {
        frame.switchPanel(new ChooseUsername(frame));
    }

    /**
     * Schließen des Quiz-Spiels.
     */
    private void quitGame() {
        System.exit(0); 
    }
}
