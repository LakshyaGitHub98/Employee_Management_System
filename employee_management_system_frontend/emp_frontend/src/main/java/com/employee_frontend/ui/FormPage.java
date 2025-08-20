package com.employee_frontend.ui;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.employee_frontend.models.Employee;
import com.employee_frontend.services.EmployeeApiService;

public class FormPage {
    public static void createAndShowGUI() {

        // Create the main frame
        JFrame frame = new JFrame("Employee Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create a panel to hold the content
        JPanel formJPanel = new JPanel();
        JLabel formLabel = new JLabel("Employee Form", JLabel.CENTER);
        JLabel formDescription = new JLabel("Please fill out the form below:", JLabel.CENTER);

        
        formLabel.setFont(formLabel.getFont().deriveFont(24.0f)); // Set larger font for the title
        
        // Set layout for the panel
        formJPanel.setLayout(new java.awt.GridLayout(0, 1)); // Use GridLayout for better organization
        
        // Set alignment for labels
        formLabel.setHorizontalAlignment(JLabel.CENTER);
        formDescription.setHorizontalAlignment(JLabel.CENTER);
        
        // Making fields and labels
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        JLabel DesignationLabel = new JLabel("Designation:");
        JTextField DesignationField = new JTextField(20);
        JLabel SalaryLabel = new JLabel("Salary:");
        JTextField SalaryField = new JTextField(20);
        JButton submitButton = new JButton("Submit");


        // Add fields to the panel
        formJPanel.add(formLabel);
        formJPanel.add(formDescription);        
        formJPanel.add(nameLabel);
        formJPanel.add(nameField); 
        formJPanel.add(emailLabel);
        formJPanel.add(emailField);
        formJPanel.add(DesignationLabel);
        formJPanel.add(DesignationField);
        formJPanel.add(SalaryLabel);
        formJPanel.add(SalaryField); 
        formJPanel.add(submitButton);


        
        // Add action listener for the submit button
        submitButton.addActionListener(e -> {
            // Here you would handle the form submission, e.g., save the data
            String name = nameField.getText();
            String email = emailField.getText();
            String designation = DesignationField.getText();
            Long salary = Long.parseLong(SalaryField.getText().trim());

            // For now, just print the values to the console
            System.out.println("    Submitted: " + name + ", " + email + ", " + designation + ", " + salary);
            // Optionally, you can clear the fields after submission
            EmployeeApiService employeeApiService = new EmployeeApiService();
            Employee emp = new Employee(name, email, designation, salary);
            try {
                employeeApiService.addEmployee(emp);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            nameField.setText("");
            emailField.setText("");
            DesignationField.setText("");
            SalaryField.setText("");
        });                    
        
        // Add the panel to the frame
        frame.add(formJPanel);

        // Set the frame visibility
        frame.setVisible(true);
    }    
    
}
