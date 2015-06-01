package com.hyrd.hyrd;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;


public class continue_employee_create extends ActionBarActivity {

    Button newButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continue_employee_create);

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

    public void newSkill (View v) {
        View b = findViewById(R.id.newSkillButton);
        b.setVisibility(View.GONE);

        View linearLayout =  findViewById(R.id.layoutEmp);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        EditText newET = new EditText(this);
        newET.setHint("Enter Skill");
        newET.setHintTextColor(getResources().getColor(R.color.hintColor));
        newET.setTextColor(getResources().getColor(R.color.white));
        newET.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams firstParams = params;
        firstParams.setMargins(0, 20, 0, 0);
        newET.setLayoutParams(firstParams);

        TextView newTV = new TextView(this);
        newTV.setText("Select a value between 0 and 100 to represent your knowledge of this skill.");
        newTV.setTextColor(getResources().getColor(R.color.white));
        newTV.setLayoutParams(params);

        DiscreteSeekBar discreteSeekBar = new DiscreteSeekBar(this);
        discreteSeekBar.setMin(0);
        discreteSeekBar.setMax(100);
        discreteSeekBar.setProgress(0);
        discreteSeekBar.setScrubberColor(getResources().getColor(R.color.gold));
        discreteSeekBar.setThumbColor(getResources().getColor(R.color.gold),getResources().getColor(R.color.gold));
        LinearLayout.LayoutParams seekBarParams = params;
        seekBarParams.setMargins(0, 20, 0, 0);
        discreteSeekBar.setLayoutParams(seekBarParams);

        newButton = new Button(this);
        newButton.setText("Add Another Skill");
        newButton.setTextColor(getResources().getColor(R.color.white));
        newButton.setLayoutParams(params);
        newButton.setBackgroundResource(R.drawable.button_login);
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newButton.setVisibility(View.GONE);
                newSkill(v);
            }
        });

        ((LinearLayout) linearLayout).addView(newET);
        ((LinearLayout) linearLayout).addView(newTV);
        ((LinearLayout) linearLayout).addView(discreteSeekBar);
        ((LinearLayout) linearLayout).addView(newButton);
    }

    public void clickNext (View v) {
        Intent employee = new Intent(continue_employee_create.this, upload_profile_pic.class);
        employee.putExtra("Source", "from Employee");
        startActivity(employee);
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
