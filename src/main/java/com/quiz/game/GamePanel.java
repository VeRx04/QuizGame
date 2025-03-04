package com.quiz.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quiz.data.ConnectionManager;
import com.quiz.data.Option;
import com.quiz.data.Queries;
import com.quiz.data.Question;
import com.quiz.main.Frame;
import com.quiz.util.*;

/**
 * Hauptpanel für das Quiz-Spiel. Verwaltet Fragen, Antworten und den Spielfortschritt.
 */
public class GamePanel extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1061525715191511874L;
	
	private static final Logger logger = LoggerFactory.getLogger(GamePanel.class);
    private final Frame frame;
    private JLabel scoreLabel;
    private JLabel livesLabel;
    private JLabel timeLabel;
    private int score;
    private int lives;
    private int timeRemaining;
    private Timer timer;
    private List<Question> questions;
    private Question currentQuestion;

    public GamePanel(Frame frame) {
        this.frame = frame;
        initGamePanel();
    }

    /**
     * Initialisiert das Spiel-Panel mit allen UI-Komponenten.
     */
    private void initGamePanel() {
        score = 0;
        lives = 3;
        timeRemaining = UserSession.getTimeLimit();

        loadQuestions();
        setLayout(new BorderLayout());
        setBackground(Config.PANEL_BG_COLOR);

        startTimer(); 
        addComponents(); 
    }

    /**
     * Lädt Fragen aus der Datenbank basierend auf Benutzerwahl.
     */
    private void loadQuestions() {
        try {
            Queries queries = new Queries();
            int questionCount = UserSession.getQuestionCount(); 
            this.questions = queries.getQuestions(
                    UserSession.getCategory().ordinal() + 1,
                    UserSession.getDifficulty().ordinal() + 1,
                    new ConnectionManager().getConnection(),
                    questionCount
            );

            if (questions.isEmpty()) {
                throw new IllegalStateException("Keine Fragen gefunden.");
            }
            Collections.shuffle(questions);
            currentQuestion = questions.get(0);
            logger.info("{} Fragen erfolgreich geladen.", questions.size());
        } 
        
        catch (Exception e) {
            logger.error("Fehler beim Laden der Fragen.", e);         
            System.exit(100);
        }
    }


    /**
     * Startet den Spiel-Timer.
     */
    private void startTimer() {
        timer = new Timer(1000, e -> {
            timeRemaining--;
            updateTimeLabel();
            if (timeRemaining <= 0) {
                endGame();
            }
        });
        timer.start();
    }

    /**
     * Erstellt und fügt UI-Elemente zum Panel hinzu.
     */
    private void addComponents() {
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel topPanel = createTopPanel();
        this.add(topPanel, BorderLayout.NORTH);

        if (currentQuestion == null) {
            showErrorMessage();
            return;
        }

        JPanel questionPanel = createQuestionPanel(); // ✅ Jetzt definiert
        this.add(questionPanel, BorderLayout.CENTER);
    }
    
    /**
     * Erstellt das Panel mit Score, Leben und Zeit.
     * @return Das Panel mit Score, Leben und Zeit.
     */
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Config.PANEL_BG_COLOR);
        topPanel.setPreferredSize(new Dimension(600, 50));

        scoreLabel = Component.createLabel("Score: " + score, new Rectangle(0, 0, 200, 30), Config.COMPONENT_FONT);
        livesLabel = Component.createLabel("Lives: " + lives, new Rectangle(0, 0, 200, 30), Config.COMPONENT_FONT);
        timeLabel = Component.createLabel("Time: " + formatTime(timeRemaining), new Rectangle(0, 0, 200, 30), Config.COMPONENT_FONT);

        topPanel.add(createScorePanel(), BorderLayout.WEST);
        topPanel.add(createTimePanel(), BorderLayout.CENTER);
        topPanel.add(createLivesPanel(), BorderLayout.EAST);

        return topPanel;
    }

    /**
     * Erstellt das Panel für die Anzeige des Punktestands.
     * @return Das Panel mit dem Punktestand
     */
    private JPanel createScorePanel() {
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        scorePanel.setBackground(Config.PANEL_BG_COLOR);
        scorePanel.add(scoreLabel);
        return scorePanel;
    }

    /**
     * Erstellt das Panel für die Anzeige der verbleibenden Zeit.
     * @return Das Panel mit der verbleibenden Zeit
     */
    private JPanel createTimePanel() {
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        timePanel.setBackground(Config.PANEL_BG_COLOR);
        timePanel.add(timeLabel);
        return timePanel;
    }

    /**
     * Erstellt das Panel für die Anzeige der verbleibenden Leben.
     * @return Das Panel mit den verbleibenden Leben
     */
    private JPanel createLivesPanel() {
        JPanel livesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        livesPanel.setBackground(Config.PANEL_BG_COLOR);
        livesPanel.add(livesLabel);
        return livesPanel;
    }


    /**
     * Erstellt das Panel mit der aktuellen Frage und zugehöriger Antwortmöglichkeiten.
     * @return Das Panel mit Fragen und Antwortmöglichkeiten
     */
    private JPanel createQuestionPanel() {
        JPanel questionPanel = new JPanel(new BorderLayout(10, 10));
        questionPanel.setBackground(Config.PANEL_BG_COLOR);
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel questionLabel = Component.createLabel(
                "<html><div style='text-align: center;'>" + currentQuestion.getQuestionText() + "</div></html>",
                new Rectangle(0, 0, 600, 100),
                Config.CARD_FONT
        );
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionPanel.add(questionLabel, BorderLayout.NORTH);

        JPanel answerPanel = createAnswerPanel();
        questionPanel.add(answerPanel, BorderLayout.CENTER);

        return questionPanel;
    }

    /**
     * Erstellt das Panel mit Antwortmöglichkeiten.
     * @return Das Panel mit den Antwortmöglichkeiten
     */
    private JPanel createAnswerPanel() {
        JPanel answerPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        answerPanel.setBackground(Config.PANEL_BG_COLOR);
        answerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        List<Option> options = new ArrayList<>(currentQuestion.getOptions());
        if (options.size() < 4) {
            logger.warn("Weniger als 4 Antwortmöglichkeiten für Frage: {}", currentQuestion.getQuestionText());
        }
        
        Collections.shuffle(options);

        for (int i = 0; i < 4; i++) { 
            Option option = options.get(i);
            JButton answerButton = Component.createButton(TextFormatter.formatText(option.getOptionText()), new Rectangle(0, 0, 300, 80));
            answerButton.addActionListener(e -> handleAnswer(option));
            answerPanel.add(answerButton);
        }

        return answerPanel;
    }

    /**
     * Verarbeitet die Auswahl einer Antwort und überprüft, ob sie korrekt ist.
     */
    private void handleAnswer(Option selectedOption) {
        if (selectedOption.isCorrect()) {
            score += 10;
            frame.playSound(com.quiz.util.Resources.CORRECT_SOUND);
        } 
        
        else {
            lives--;
            frame.playSound(com.quiz.util.Resources.WRONG_SOUND);
            if (lives <= 0) {
                endGame();
                return;
            }
        }
        updateScoreLabel();
        updateLivesLabel();
        loadNextQuestion();
    }

    /**
     * Lädt die nächste Frage oder beendet das Spiel, falls keine Fragen mehr übrig sind.
     */
    private void loadNextQuestion() {
        int currentIndex = questions.indexOf(currentQuestion);
        if (currentIndex + 1 < questions.size()) {
            currentQuestion = questions.get(currentIndex + 1);
            removeAll();
            addComponents();
            revalidate();
            repaint();
        } 
        
        else {
            endGame();
        }
    }

    /**
     * Beendet das Spiel und speichert den Score.
     */
    private void endGame() {
        timer.stop();

        // Score speichern
        Queries queries = new Queries();
        queries.saveUserScore(UserSession.getUsername(), score);

        frame.switchPanel(new Endscreen(frame, score));
        frame.stopMusic();
        logger.info("Spiel beendet. Endpunktzahl: {} für Spieler {}.", score, UserSession.getUsername());
    }

    /**
     * Aktualisiert das Zeit-Label.
     */
    private void updateTimeLabel() {
        timeLabel.setText("Time: " + formatTime(timeRemaining));
    }

    /**
     * Formatiert die Zeit in mm:ss.
     * @return Die formatierte Zeitangabe
     */
    private String formatTime(int timeInSeconds) {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Zeigt eine Fehlermeldung, wenn keine Fragen geladen werden konnten.
     */
    private void showErrorMessage() {
        JOptionPane.showMessageDialog(this, "Es konnten keine Fragen geladen werden!", "Fehler", JOptionPane.ERROR_MESSAGE);
        frame.switchPanel(new ChooseUsername(frame));
    }

    /**
     * Aktualisiert das Score-Label.
     */
    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Aktualisiert das Leben-Label.
     */
    private void updateLivesLabel() {
        livesLabel.setText("Lives: " + lives);
    }
}
