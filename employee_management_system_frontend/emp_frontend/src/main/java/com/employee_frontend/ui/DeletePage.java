package com.employee_frontend.ui;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.employee_frontend.services.EmployeeApiService;

public class DeletePage {
    public static void createAndShowGUI() {
        // Implementation for DeletePage GUI goes here
        // This method should create the UI components for deleting an employee
        // and handle the deletion logic using EmployeeApiService.  
    
        // Create the main frame
        JFrame frame = new JFrame("Delete Employee");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Delete Employee Page", JLabel.CENTER);
        JLabel idLabel = new JLabel("Enter Employee ID to delete:");
        JTextField idField = new JTextField(20);
        JButton deleteButton = new JButton("Delete Employee");


        deleteButton.addActionListener(e -> {
            String idText = idField.getText();
            if (!idText.isEmpty()) {
                try {
                    Long id = Long.parseLong(idText);
                    EmployeeApiService apiService = new EmployeeApiService();
                    apiService.deleteEmployee(id);
                    JLabel successLabel = new JLabel("Employee deleted successfully!", JLabel.CENTER);
                    panel.add(successLabel);
                }
                catch (NumberFormatException ex) {
                    JLabel errorLabel = new JLabel("Invalid ID format. Please enter a valid number.", JLabel.CENTER);
                    panel.add(errorLabel);
                } catch (IOException e1) {
                    JLabel errorLabel = new JLabel("Error deleting employee. Please try again.", JLabel.CENTER);
                    panel.add(errorLabel);
                    e1.printStackTrace();
                } 
            }
            else {
                JLabel errorLabel = new JLabel("ID field cannot be empty.", JLabel.CENTER);
                panel.add(errorLabel);
            }    
        });
        
        panel.add(label);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(deleteButton);
        panel.setLayout(new java.awt.GridLayout(0, 1)); // Use GridLayout for better organization   
        
        // Add the panel to the frame
        frame.add(panel);

        // Set the frame visibility
        frame.setVisible(true);
    }    

}
