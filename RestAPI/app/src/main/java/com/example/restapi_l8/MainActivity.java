package com.example.restapi_l8;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Get all employees
//        EmployeeService.getAllEmployees(this);

        // Get employee by ID
//        EmployeeService.getEmployeeById(this, 1);

        // Add a new employee
//        Employee newEmployee = new Employee();
//        newEmployee.setFirstname("abc");
//        newEmployee.setLastname("xyz");
//        newEmployee.setEmail("abc.xyz@example.com");
//        newEmployee.setDepartment("HR");
//        newEmployee.setSalary(50000);
//        newEmployee.setJoiningDate("2023-01-15");
//
//        EmployeeService.addEmployee(this, newEmployee);

        // Update an employee
//        newEmployee.setSalary(55000); // Example update
//        EmployeeService.updateEmployee(this, 13, newEmployee);

        // Delete an employee
        EmployeeService.deleteEmployee(this, 13);



    }
}