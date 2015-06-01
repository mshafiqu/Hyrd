package com.hyrd.hyrd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class new_employee extends ActionBarActivity {
    private ArrayList<String> selectedItems=new ArrayList<>();
    EditText firstNameNewEmp;
    EditText lastNameNewEmp;
    EditText phoneEmp;
    EditText emailNewEmp;
    EditText selectedDepartmentEmp;
    String salaryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

        EditText password = (EditText) findViewById(R.id.passwordNewEmp);
        password.setTransformationMethod(new PasswordTransformationMethod());
        addListenerOnSpinnerItemSelection();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle(null);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        firstNameNewEmp = (EditText) findViewById(R.id.firstNameNewEmp);
        lastNameNewEmp = (EditText) findViewById(R.id.lastNameNewEmp);
        phoneEmp = (EditText) findViewById(R.id.phoneEmp);
        emailNewEmp = (EditText) findViewById(R.id.emailNewEmp);
        selectedDepartmentEmp = (EditText) findViewById(R.id.selectedDepartmentEmp);
    }

    public void clickSelectDepartment(View v) {
        final CharSequence[] items = {"Software Engineering","Computer Engineering","Marketing","Information Technology"};
        final EditText selectedDepartmentEmp = (EditText) findViewById(R.id.selectedDepartmentEmp);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Department(s)");
        builder.setMultiChoiceItems(items, null,
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which,
                                        boolean isChecked) {
                        if (isChecked) {
                            // If the user checked the item, add it to the selected items
                            selectedItems.add(items[which].toString());
                        } else if (selectedItems.contains(items[which].toString())) {
                            // Else, if the item is already in the array, remove it
                            selectedItems.remove(items[which].toString());
                        }
                    }
                })
                // Set the action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String selectedDepartments = selectedItems.toString();
                        selectedDepartmentEmp.setText(selectedDepartments.substring(1, selectedDepartments.length()-1));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (selectedItems.isEmpty()) {
                            selectedDepartmentEmp.setText("Department(s)");
                        }
                    }
                });
        builder.show();
    }

    public void addListenerOnSpinnerItemSelection() {
        Spinner salary = (Spinner) findViewById(R.id.salarySpinner);
        ArrayAdapter<CharSequence> salaryAdapter = ArrayAdapter.createFromResource(
                this, R.array.salary_arrays, R.layout.spinner_layout);
        salaryAdapter.setDropDownViewResource(R.layout.spinner_layout);
        salary.setPopupBackgroundResource(R.drawable.spinner_background);
        salary.setAdapter(salaryAdapter);
        salary.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                salaryString = parent.getItemAtPosition(position).toString();
                Log.d("Salary", salaryString);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void continueEmp (View v) {
        DBHandler dbHandler = new DBHandler(this, null, null, 1);

        String first_name = firstNameNewEmp.getText().toString();
        String last_name = lastNameNewEmp.getText().toString();
        String phone_number = phoneEmp.getText().toString();
        String emp_email = emailNewEmp.getText().toString();
        String emp_department = selectedDepartmentEmp.getText().toString();
        String salary = salaryString;

        Employee employee = new Employee(first_name, last_name, phone_number, emp_email, emp_department, salary);

        dbHandler.addEmployee(employee);

        Intent continueEmp = new Intent(new_employee.this, continue_employee_create.class);
        startActivity(continueEmp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
