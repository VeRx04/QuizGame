package com.quiz.main;

import java.awt.Component;
import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.quiz.event.KeyHandler;
import com.quiz.game.ChooseUsername;
import com.quiz.util.*;

/**
 * Hauptfenster für die Quiz-Anwendung.
 */
public class Frame extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3843191995657243022L;
	
	private static final Logger logger = LoggerFactory.getLogger(Frame.class);
    private Clip backgroundMusic;
    public boolean isFullscreen = false;
    private final GraphicsDevice device;

    public Frame() {
        super(Config.WINDOW_TITLE);
        setIconImage(new ImageIcon(getClass().getResource(Resources.APP_ICON)).getImage());

        setSize(Config.WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        
        KeyHandler keyHandler = new KeyHandler(this);
        addKeyListener(keyHandler);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();  
            }
        });

        setLocationRelativeTo(null);
        switchPanel(new ChooseUsername(this));
        setVisible(true);
    }

    /**
     * Wechselt den aktuellen Panel-Inhalt.
     * @param panel Neues Panel
     */
    public void switchPanel(JPanel panel) {
        setContentPane(panel);
        refreshUI();
        requestFocusForFrame();
    }
    
    /**
     * Setzt den Fokus auf das Hauptfenster, damit Tasteneingaben sofort erkannt werden.
     */
    public void requestFocusForFrame() {
        SwingUtilities.invokeLater(() -> {
            requestFocusInWindow(); 
            toFront(); 
            setFocusable(true);
        });
    }

    /**
     * Wechselt zwischen Vollbild- und Fenstermodus.
     */
    public void toggleFullscreen() {
        SwingUtilities.invokeLater(() -> {
            dispose(); 
            isFullscreen = !isFullscreen;
            setUndecorated(isFullscreen);
            device.setFullScreenWindow(isFullscreen ? this : null);
            setSize(isFullscreen ? getToolkit().getScreenSize() : Config.WINDOW_SIZE);
            TextFormatter.setFullscreenMode(isFullscreen);
            refreshTextComponents();
            refreshUI();
            setVisible(true);
            requestFocusInWindow();
        });
    }

    /**
     * Aktualisiert die Textdarstellung aller UI-Komponenten.
     */
    private void refreshTextComponents() {
        if (getContentPane() instanceof JPanel panel) {
            updatePanelTexts(panel);
        }
    }

    /**
     * Durchläuft alle Komponenten eines Panels und aktualisiert den Text.
     */
    private void updatePanelTexts(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JButton button) {
                button.setText(TextFormatter.formatText(button.getText().replace("<html><div style='text-align: center;'>", "").replace("</div></html>", "")));
            }             
            
            else if (comp instanceof Container subContainer) {
                updatePanelTexts(subContainer); 
            }
        }
    }

    /**
     * Aktualisiert die Benutzeroberfläche.
     */
    private void refreshUI() {
        revalidate();
        repaint();
    }

    /**
     * Spielt einen Soundeffekt ab.
     * @param filePath Dateipfad zum Sound
     */
    public void playSound(String filePath) {
        try {
            // Lade Sound aus dem Classpath
            java.net.URL soundUrl = getClass().getResource(filePath);
            if (soundUrl == null) {
                logger.warn("Datei nicht gefunden: {}", filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundUrl);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } 
        catch (Exception e) {
            logger.error("Fehler beim Abspielen des Sounds.", e);
        }
    }


    /**
     * Spielt Hintergrundmusik ab.
     * @param filePath Dateipfad zur Musikdatei aus dem Classpath
     */
    public void playMusic(String filePath) {
        try {
            java.net.URL musicUrl = getClass().getResource(filePath);
            if (musicUrl == null) {
                logger.warn("Musikdatei nicht gefunden: {}", filePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicUrl);
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();
        } 
        catch (Exception e) {
            logger.error("Fehler beim Abspielen der Musik.", e);
        }
    }

    /**
     * Stoppt die aktuell spielende Musik.
     */
    public void stopMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
            backgroundMusic.close();
        }
    }
}
