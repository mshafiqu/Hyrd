package com.hyrd.hyrd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class new_employee extends ActionBarActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private int count = 1;
    private ArrayList<String> selectedDepartment = new ArrayList<>();
    private ArrayList<String> selectedItems=new ArrayList<>();
    EditText firstNameNewEmp;
    EditText lastNameNewEmp;
    EditText phoneEmp;
    EditText emailNewEmp;
    EditText selectedDepartmentEmp;
    EditText passwordNewEmp;
    String salaryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Ubuntu-R.ttf");
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

        String[] departments_array = getResources().getStringArray(R.array.departments_arrays);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.selectedDepartmentEmp);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,departments_array);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                if (count > 1) {
                    Log.e("AutoCompleteTextView", "More than 1 item selected");
                } else {
                    count++;
                    String selection = (String) parent.getItemAtPosition(position);
                    addDepartmentTextView(selection);
                }
            }
        });
    }

    public void addDepartmentTextView(final String selection) {
        View linearLayout =  findViewById(R.id.departmentEmpLayout);
        LinearLayout.LayoutParams params = new TableLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1f);

        final TextView newTV = new TextView(this);
        newTV.setText(selection);
        newTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        newTV.setTextColor(getResources().getColor(R.color.white));
        newTV.setGravity(Gravity.CENTER);
        newTV.setBackground(getResources().getDrawable(R.drawable.skills_button));
        newTV.setLayoutParams(params);

        autoCompleteTextView.setText("");
        selectedDepartment.add(selection);

        ((LinearLayout) linearLayout).addView(newTV);

        newTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(new_employee.this);
                builder.setMessage("Are you sure you want to delete this department?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                newTV.setVisibility(View.GONE);
                                autoCompleteTextView.setEnabled(true);
                                selectedDepartment.remove(selection);
                                count--;
                                dialog.cancel();
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
        if (count > 1) {
            autoCompleteTextView.setEnabled(false);
        }
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
        String emp_password = passwordNewEmp.getText().toString();
        String emp_department = selectedDepartmentEmp.getText().toString();
        String salary = salaryString;

        Employee employee = new Employee(first_name, last_name, phone_number, emp_email, emp_password, emp_department, salary);

        dbHandler.addEmployee(employee);

        Intent continueEmp = new Intent(new_employee.this, add_skill.class);
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
