package com.hyrd.hyrd;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class profile_view extends ActionBarActivity {

    TextView employeeID;
    EditText firstName;
    EditText lastName;
    EditText phoneNumber;
    EditText email;
    EditText department;
    EditText salary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        employeeID = (TextView) findViewById(R.id.employeeID);
        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        phoneNumber = (EditText) findViewById(R.id.phone_number);
        email = (EditText) findViewById(R.id.email);
        department = (EditText) findViewById(R.id.department);
        salary = (EditText) findViewById(R.id.salary);
    }

    public void newEmployee(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        String first_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        String phone_number = phoneNumber.getText().toString();
        String emp_email = email.getText().toString();
        String emp_department = department.getText().toString();
        String emp_salary = salary.getText().toString();

        Employee employee = new Employee(first_name, last_name, phone_number, emp_email, emp_department, emp_salary);

        dbHandler.addEmployee(employee);
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        email.setText("");
        department.setText("");
        salary.setText("");
    }

    public void lookupEmployee (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Employee employee = dbHandler.findEmployee(firstName.getText().toString());

        if (employee != null) {
            employeeID.setText(String.valueOf(employee.getID()));
            firstName.setText(employee.getFirstName());
            lastName.setText(employee.getLastName());
            phoneNumber.setText(employee.getPhoneNumber());
            email.setText(employee.getEmail());
            department.setText(employee.getDepartment());
            salary.setText(employee.getSalary());
        } else {
            employeeID.setText("No Match Found");
        }
    }

    public void removeEmployee(View view) {
        DBHandler dbHandler = new DBHandler(this, null,
                null, 1);

        boolean result = dbHandler.deleteEmployee(firstName.getText().toString());

        if (result)
        {
            employeeID.setText("Record Deleted");
            firstName.setText("");
            lastName.setText("");
            phoneNumber.setText("");
            email.setText("");
            department.setText("");
            salary.setText("");
        }
        else
            employeeID.setText("No Match Found");
    }

    public void clearFields(View view) {
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        email.setText("");
        department.setText("");
        salary.setText("");
    }

    public void allEmployees(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        List<Employee> allEmployees = dbHandler.getAllEmployees();

        for (Employee employee : allEmployees) {
            Log.d("First Name:", employee.getFirstName());
            Log.d("Last Name:", employee.getLastName());
            Log.d("Phone Number:", employee.getPhoneNumber());
            Log.d("Email:", employee.getEmail());
            Log.d("Department:", employee.getDepartment());
            Log.d("Salary:", employee.getSalary());
        }
    }
}
