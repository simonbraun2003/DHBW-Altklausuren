package de.dhbwka.evasionator.classes;

import javax.swing.*;

public class EvasionException extends RuntimeException {
    public EvasionException(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
