package com.quiz.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;

import com.quiz.util.*;

/**
 * Eine benutzerdefiniertes Knopf-UI.
 */
public class ModifiedButton extends JButton {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5456512642598124241L;

	/**
     * Erstellt eine Schaltfläche mit angepasstem Design.
     * @param text Der Text, der auf der Schaltfläche angezeigt wird.
     */
    public ModifiedButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setFocusable(false);
    }

    /**
     * Zeichnet die Schaltfläche mit abgerundeten Ecken und der definierten Konfiguration.
     * @param g Das Grafikobjekt für die Zeichnung.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), Config.BORDER_RADIUS, Config.BORDER_RADIUS);

        g2d.setColor(Config.BORDER_COLOR);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, Config.BORDER_RADIUS, Config.BORDER_RADIUS);

        super.paintComponent(g);
    }
}
