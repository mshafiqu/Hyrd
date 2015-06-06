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
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class new_recruiter extends ActionBarActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private int count = 1;
    private ArrayList<String> selectedDepartment = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Ubuntu-R.ttf");
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

        String[] departments_array = getResources().getStringArray(R.array.departments_arrays);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.selectedDepartmentRec);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,departments_array);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                if (count > 1) {
                    Log.e("AutoCompleteTextView", "More than 1 items selected");
                } else {
                    count++;
                    String selection = (String) parent.getItemAtPosition(position);
                    addDepartmentTextView(selection);
                }
            }
        });
    }

    public void addDepartmentTextView(final String selection) {
        View linearLayout =  findViewById(R.id.departmentRecLayout);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(new_recruiter.this);
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
