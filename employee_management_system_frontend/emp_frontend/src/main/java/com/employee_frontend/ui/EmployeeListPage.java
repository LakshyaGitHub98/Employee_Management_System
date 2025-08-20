package com.employee_frontend.ui;

import com.employee_frontend.services.EmployeeApiService;
import com.employee_frontend.models.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EmployeeListPage {

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("All Employees");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Name", "Designation", "Salary"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        try {
            EmployeeApiService service = new EmployeeApiService();
            List<Employee> employees = service.getAllEmployees();

            for (Employee emp : employees) {
                Object[] row = {
                    emp.getId(),
                    emp.getName(),
                    emp.getDesignation(),
                    emp.getSalary()
                };
                tableModel.addRow(row);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to fetch employees: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}