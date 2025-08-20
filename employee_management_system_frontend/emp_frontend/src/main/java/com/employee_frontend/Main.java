package com.employee_frontend;

import javax.swing.*;
import com.employee_frontend.ui.HomePage;

public class Main {
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HomePage.createAndShowGUI();
        });
    }
}