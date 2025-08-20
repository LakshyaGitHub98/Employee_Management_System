package com.employee_frontend.ui;

import javax.swing.*;
import java.awt.GridLayout;

public class HomePage {
    public static void createAndShowGUI() {

        JFrame frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JLabel label = new JLabel("Welcome to the Employee Management System", JLabel.CENTER);
        label.setFont(label.getFont().deriveFont(24.0f));
        panel.add(label);

        // Add Employee
        JButton addButton = new JButton("Add Employee");
        addButton.addActionListener(e -> FormPage.createAndShowGUI());
        panel.add(addButton);

        // View All Employees
        JButton viewAllButton = new JButton("View All Employees");
        viewAllButton.addActionListener(e -> EmployeeListPage.createAndShowGUI());
        panel.add(viewAllButton);

        // Delete Employee
        JButton deleteButton = new JButton("Delete Employee");
        deleteButton.addActionListener(e -> DeletePage.createAndShowGUI());
        panel.add(deleteButton);

        // Update Employee (with ID prompt)
        JButton updateButton = new JButton("Update Employee");
        updateButton.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter Employee ID to update:");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    Long employeeId = Long.parseLong(input.trim());
                    UpdatePage.createAndShowGUI(employeeId);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid ID format.");
                }
            }
        });
        panel.add(updateButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}