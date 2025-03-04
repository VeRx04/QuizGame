package com.quiz.util;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JLabel;

import com.quiz.component.ModifiedButton;
import com.quiz.component.ModifiedTextfield;

/**
 * Hilfsklasse zur Erstellung von UI-Komponenten.
 */
public class Component {

    /**
     * Erstellt ein Label mit benutzerdefiniertem Text, Größe und Font.
     * @param text   Der anzuzeigende Text.
     * @param bounds Die Position und Größe des Labels.
     * @param font   Die Schriftart des Labels.
     * @return Das konfigurierte JLabel.
     */
    public static JLabel createLabel(String text, Rectangle bounds, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(Config.TEXT_COLOR);
        label.setBounds(bounds);
        return label;
    }

    /**
     * Erstellt eine Schaltfläche mit einem bestimmten Text und Größe.
     * @param text   Der anzuzeigende Text.
     * @param bounds Die Position und Größe der Schaltfläche.
     * @return Das konfigurierte JButton-Objekt.
     */
    public static ModifiedButton createButton(String text, Rectangle bounds) {
        ModifiedButton button = new ModifiedButton(text);
        Effect.configureComponent(button, bounds, Config.COMPONENT_FONT);
        Effect.applyHoverEffects(button);
        return button;
    }

    /**
     * Erstellt ein benutzerdefiniertes Textfeld.
     * @param bounds Die Position und Größe des Textfelds.
     * @return Das konfigurierte JTextField.
     */
    public static ModifiedTextfield createTextField(Rectangle bounds) {
        ModifiedTextfield textField = new ModifiedTextfield(15, 15, 15, 15);
        Effect.configureComponent(textField, bounds, Config.COMPONENT_FONT);
        Effect.applyHoverEffects(textField);
        return textField;
    }
}
