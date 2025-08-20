package com.employee_frontend.services;

import org.json.JSONArray;
import org.json.JSONObject;
import com.employee_frontend.models.Employee;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EmployeeApiService {

    private static final String BASE_URL = "http://localhost:8080/employees";

    public List<Employee> getAllEmployees() throws IOException {
        URL url = new URL(BASE_URL + "/read");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        JSONArray jsonArray = new JSONArray(response.toString());
        List<Employee> employees = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Employee emp = new Employee();
            emp.setId(obj.getLong("id"));
            emp.setName(obj.getString("name"));
            emp.setEmail(obj.getString("email"));
            emp.setDesignation(obj.getString("designation"));
            emp.setSalary(obj.getLong("salary"));
            employees.add(emp);
        }

        return employees;
    }

    public void addEmployee(Employee emp) throws IOException {
        JSONObject json = new JSONObject();
        json.put("name", emp.getName());
        json.put("designation", emp.getDesignation());
        json.put("email", emp.getEmail());
        json.put("salary", emp.getSalary());

        URL url = new URL(BASE_URL + "/create");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.toString().getBytes());
        }

        conn.getResponseCode();
    }

    public static void updateEmployee(Employee emp) throws IOException {
        if (emp.getId() == 0) {
            throw new IllegalArgumentException("Employee ID cannot be null for update");
        }

        JSONObject json = new JSONObject();
        json.put("name", emp.getName());
        json.put("designation", emp.getDesignation());
        json.put("email", emp.getEmail());
        json.put("salary", emp.getSalary());

        URL url = new URL(BASE_URL + "/update/" + emp.getId());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.toString().getBytes());
        }

        conn.getResponseCode();
    }

    public void deleteEmployee(Long id) throws IOException {
        URL url = new URL(BASE_URL + "/delete/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.getResponseCode();
    }

    public static Employee getEmployeeById(Long employeeId) throws IOException {
        URL url = new URL(BASE_URL + "/read/" + employeeId);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        JSONObject obj = new JSONObject(response.toString());
        Employee emp = new Employee();
        emp.setId(obj.getLong("id"));
        emp.setName(obj.getString("name"));
        emp.setEmail(obj.getString("email"));
        emp.setDesignation(obj.getString("designation"));
        emp.setSalary(obj.getLong("salary"));

        return emp;
    }
}