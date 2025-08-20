package com.employee_frontend.ui;

import javax.swing.*;
import com.employee_frontend.models.Employee;
import com.employee_frontend.services.EmployeeApiService;
import java.io.IOException;

public class UpdatePage {
    public static void createAndShowGUI(Long employeeId) {
        Employee existingEmp = null;

        try {
            existingEmp = EmployeeApiService.getEmployeeById(employeeId); // 👈 Fetch from backend
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to fetch employee data.");
            return; // ❌ Stop if data not available
        }

        JFrame frame = new JFrame("Update Employee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel formJPanel = new JPanel();
        formJPanel.setLayout(new java.awt.GridLayout(0, 1));

        JLabel formLabel = new JLabel("Employee Form", JLabel.CENTER);
        JLabel formDescription = new JLabel("Please fill out the form below:", JLabel.CENTER);

        JTextField nameField = new JTextField(existingEmp.getName(), 20);
        JTextField emailField = new JTextField(existingEmp.getEmail(), 20);
        JTextField designationField = new JTextField(existingEmp.getDesignation(), 20);
        JTextField salaryField = new JTextField(String.valueOf(existingEmp.getSalary()), 20);
        JButton submitButton = new JButton("Submit");

        formJPanel.add(formLabel);
        formJPanel.add(formDescription);
        formJPanel.add(new JLabel("Name:"));
        formJPanel.add(nameField);
        formJPanel.add(new JLabel("Email:"));
        formJPanel.add(emailField);
        formJPanel.add(new JLabel("Designation:"));
        formJPanel.add(designationField);
        formJPanel.add(new JLabel("Salary:"));
        formJPanel.add(salaryField);
        formJPanel.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String designation = designationField.getText();
            Long salary;

            try {
                salary = Long.parseLong(salaryField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid salary format.");
                return;
            }

            Employee updatedEmp = new Employee(name, email, designation, salary);
            updatedEmp.setId(employeeId); // ✅ ID set from parameter

            try {
                EmployeeApiService.updateEmployee(updatedEmp);
                JOptionPane.showMessageDialog(null, "Employee updated successfully!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Update failed.");
            }
        });

        frame.add(formJPanel);
        frame.setVisible(true);
    }
}