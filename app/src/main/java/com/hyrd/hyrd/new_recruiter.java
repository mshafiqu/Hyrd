package com.hyrd.hyrd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class new_recruiter extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recruiter);

        EditText password = (EditText) findViewById(R.id.passwordNewRec);
        password.setTransformationMethod(new PasswordTransformationMethod());

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

    public void clickSelectDepartment(View v) {
        final CharSequence[] items = {"Software Engineering","Computer Engineering","Marketing","Information Technology"};
        final ArrayList<String> selectedItems=new ArrayList<>();
        final EditText selectedDepartmentRec = (EditText) findViewById(R.id.selectedDepartmentRec);
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
                        selectedDepartmentRec.setText(selectedDepartments.substring(1, selectedDepartments.length()-1));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (selectedItems.isEmpty()) {
                            selectedDepartmentRec.setText("Department(s)");
                        }
                    }
                });
        builder.show();
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

    public void nextNewRec (View v) {
        Intent recruiter = new Intent(new_recruiter.this, upload_profile_pic.class);
        recruiter.putExtra("Source", "from Recruiter");
        startActivity(recruiter);
    }
}
