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
    EditText password;
    EditText skillName;
    EditText skillValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Ubuntu-R.ttf");
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
        skillName = (EditText) findViewById(R.id.skillName);
        skillValue = (EditText) findViewById(R.id.skillValue);
    }

    public void newEmployee(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        String first_name = firstName.getText().toString();
        String last_name = lastName.getText().toString();
        String phone_number = phoneNumber.getText().toString();
        String emp_email = email.getText().toString();
        String emp_password = password.getText().toString();
        String emp_department = department.getText().toString();
        String emp_salary = salary.getText().toString();
        //String skill_name = skillName.getText().toString();
        //int skill_value = Integer.parseInt(skillValue.getText().toString());

        Employee employee = new Employee(first_name, last_name, phone_number, emp_email, emp_password, emp_department, emp_salary);
        //Skills skill = new Skills(skill_name, skill_value);

        dbHandler.addEmployee(employee);
        //dbHandler.addSkill(skill);
        firstName.setText("");
        lastName.setText("");
        phoneNumber.setText("");
        email.setText("");
        department.setText("");
        salary.setText("");
        skillName.setText("");
        skillValue.setText("");
    }

    public void lookupEmployee (View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        Employee employee = dbHandler.findEmployee(firstName.getText().toString());
        List<Skills> allSkillsForEmployee = dbHandler.getAllSkillsForEmployee(employee.getID());

        if (employee != null) {
            employeeID.setText(String.valueOf(employee.getID()));
            firstName.setText(employee.getFirstName());
            lastName.setText(employee.getLastName());
            phoneNumber.setText(employee.getPhoneNumber());
            email.setText(employee.getEmail());
            department.setText(employee.getDepartment());
            salary.setText(employee.getSalary());
            Log.d(" Employee ID", String.valueOf(employee.getID()));
            Log.d("First Name:", employee.getFirstName());
            Log.d("Last Name:", employee.getLastName());
            Log.d("Phone Number:", employee.getPhoneNumber());
            Log.d("Email:", employee.getEmail());
            Log.d("Department:", employee.getDepartment());
            Log.d("Salary:", employee.getSalary());
            for (Skills skill : allSkillsForEmployee) {
                Log.d("Skill Name:", skill.getSkillName());
                Log.d("Skill Value:", String.valueOf(skill.getSkillValue()));
            }
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
        List<Skills> allSkills = dbHandler.getAllSkills();

        for (Employee employee : allEmployees) {
            Log.d(" Employee ID", String.valueOf(employee.getID()));
            Log.d("First Name:", employee.getFirstName());
            Log.d("Last Name:", employee.getLastName());
            Log.d("Phone Number:", employee.getPhoneNumber());
            Log.d("Email:", employee.getEmail());
            Log.d("Department:", employee.getDepartment());
            Log.d("Salary:", employee.getSalary());
        }
        for (Skills skill : allSkills) {
            Log.d("Skill Employee ID", String.valueOf(skill.getID()));
            Log.d("Skill Name:", skill.getSkillName());
            Log.d("Skill Value:", String.valueOf(skill.getSkillValue()));
        }
    }

    public void addSkillToEmployee(View view) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        String skill_name = skillName.getText().toString();
        int skill_value = Integer.parseInt(skillValue.getText().toString());

        Employee employee = dbHandler.findEmployee(firstName.getText().toString());
        Skills skill = new Skills(skill_name, skill_value);

        dbHandler.addSkillForEmployee(employee, skill);
    }
}
