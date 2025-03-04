package com.quiz.game;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.quiz.component.ModifiedTextfield;
import com.quiz.main.Frame;
import com.quiz.util.Component;
import com.quiz.util.Config;
import com.quiz.util.Resources;

/**
 * Panel zur Eingabe des Benutzernamens.
 */
public class ChooseUsername extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5105882624007406890L;
	
	private final Frame frame;
    private ModifiedTextfield usernameField;
    private JButton continueButton;

    /**
     * Erstellt das Panel für die Benutzernameneingabe.
     * @param frame Hauptfenster des Spiels
     */
    public ChooseUsername(Frame frame) {
        this.frame = frame;
        frame.playMusic(Resources.MAINMENU_MUSIC);
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
        gbc.insets = new Insets(10, 0, 10, 0);

        createLabels(gbc);
        createTextFields(gbc);
        createButtons(gbc);
    }

    /**
     * Erstellt und fügt die Labels zum Panel hinzu.
     * @param gbc Layout-Objekt
     */
    private void createLabels(GridBagConstraints gbc) {
        JLabel welcomeLabel = Component.createLabel(
            "<html><div style='text-align: center; font-size: " +
            Config.TITLE_FONT_SIZE + "px;'><b>Willkommen!</b><br>Bitte gib deinen Namen ein.</div></html>",
            new Rectangle(0, 0, 0, 0),
            Config.TITLE_FONT
        );
        gbc.insets = new Insets(0, 0, 100, 0);
        this.add(welcomeLabel, gbc);
    }

    /**
     * Erstellt und fügt das Textfeld für den Benutzernamen hinzu.
     * @param gbc Layout-Objekt
     */
    private void createTextFields(GridBagConstraints gbc) {
        usernameField = Component.createTextField(new Rectangle(0, 0, 0, 0));
        usernameField.setPreferredSize(new Dimension(250, 60));
        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleContinue();
                }
            }
        });

        gbc.gridy++;
        gbc.insets = new Insets(25, 0, 0, 0);
        this.add(usernameField, gbc);
    }

    /**
     * Erstellt und fügt die Buttons zum Panel hinzu.
     */
    private void createButtons(GridBagConstraints gbc) {
        continueButton = Component.createButton("Weiter", new Rectangle(0, 0, 0, 0));
        gbc.fill = GridBagConstraints.NONE;
        continueButton.setPreferredSize(new Dimension(150, 50));
        continueButton.addActionListener(e -> handleContinue());

        gbc.gridy++;
        gbc.insets = new Insets(50, 0, 0, 0);
        this.add(continueButton, gbc);
    }

    /**
     * Verarbeitet die Eingabe und speichert den Benutzernamen.
     */
    private void handleContinue() {
        String username = usernameField.getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bitte gib einen Namen ein!", "Fehlende Eingabe", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UserSession.setUsername(username);
        frame.switchPanel(new ChooseCategory(frame));
    }
}
