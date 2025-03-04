package com.quiz.component;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

import com.quiz.util.*;

/**
 * Eine benutzerdefinierte ComboBox mit angepasstem Design und Hover-Effekten.
 * @param <E> Der generische Typ für die Elemente der Dropdown-Liste.
 */
public class ModifiedComboBox<E> extends JComboBox<E> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6771683433199631157L;

	/**
     * Erstellt eine ComboBox mit angepasstem Design und speziellen Effekten.
     * @param items Die Elemente, die in der Dropdown-Liste angezeigt werden.
     */
    public ModifiedComboBox(E[] items) {
        super(items);
        setOpaque(false);
        setBackground(Config.COMPONENT_BG_COLOR);
        setForeground(Config.TEXT_COLOR);
        setFont(Config.COMPONENT_FONT);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setFocusable(false);
        setRenderer(new CustomComboBoxRenderer<>());
        setUI(new CustomComboBoxUI());
    }

    /**
     * Zeichnet die ComboBox mit abgerundeten Ecken.
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

    /**
     * Renderer für die Elemente in der ComboBox.
     * @param <E> Der generische Typ für die Elemente.
     * @return Benutzerdefinierte Inhalte der Dropbox als JLabel.
     */
    private static class CustomComboBoxRenderer<E> extends DefaultListCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = -6822254646927379485L;

		@Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            label.setFont(Config.COMPONENT_FONT);
            label.setForeground(Config.TEXT_COLOR);
            label.setBackground(isSelected ? Config.COMPONENT_BG_COLOR.brighter() : Config.COMPONENT_BG_COLOR);
            label.setBorder(new EmptyBorder(5, 5, 5, 5));
            label.setOpaque(true);
            return label;
        }
    }

    /**
     * Benutzerdefiniertes UI-Design für die ComboBox.
     * @return Benutzerdefiniertes UI-Design als JButton.
     */
    private static class CustomComboBoxUI extends javax.swing.plaf.basic.BasicComboBoxUI {
        @Override
        protected JButton createArrowButton() {
            JButton button = new JButton();
            button.setBorder(BorderFactory.createEmptyBorder());
            button.setContentAreaFilled(false);
            button.setOpaque(false);
            return button;
        }
    }
}
