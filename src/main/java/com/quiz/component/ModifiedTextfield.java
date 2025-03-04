package com.quiz.component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import com.quiz.util.*;

/**
 * Ein benutzerdefiniertes Textfeld-UI.
 */
public class ModifiedTextfield extends JTextField {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5176483940364067238L;

	/**
     * Erstellt ein Textfeld mit angepasster Innenabstandskonfiguration.
     * @param top    Abstand oben.
     * @param left   Abstand links.
     * @param bottom Abstand unten.
     * @param right  Abstand rechts.
     */
    public ModifiedTextfield(int top, int left, int bottom, int right) {
        setOpaque(false);
        setHorizontalAlignment(JTextField.CENTER);
        setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        setCaretColor(Config.TEXT_COLOR);
    }

    /**
     * Zeichnet das Textfeld mit abgerundeten Ecken.
     * @param g Das Grafikobjekt für die Zeichnung.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), Config.BORDER_RADIUS, Config.BORDER_RADIUS);

        g2d.setColor(Config.BORDER_COLOR);
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, Config.BORDER_RADIUS, Config.BORDER_RADIUS);

        g2d.dispose();
        super.paintComponent(g);
    }
}
