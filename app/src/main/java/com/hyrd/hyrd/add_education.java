package com.hyrd.hyrd;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class add_education extends ActionBarActivity {

    private TextView startDateDisplay;
    private TextView endDateDisplay;
    private Button startPickDate;
    private Button endPickDate;
    private Calendar startDate;
    private Calendar endDate;

    static final int DATE_DIALOG_ID = 0;

    private TextView activeDateDisplay;
    private Calendar activeDate;

    private Calendar newStartDate;
    private Calendar newEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education);

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

    public void addHighSchool(View v) {
        View linearLayout =  findViewById(R.id.highSchoolLayout);
        Button originalAddButton = (Button) findViewById(R.id.addHighSchoolButton);
        originalAddButton.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        final CheckBox currentStudent = new CheckBox(this);
        currentStudent.setText("Currently Attending");
        currentStudent.setTextColor(getResources().getColor(R.color.white));

        EditText highSchoolEt = new EditText(this);
        highSchoolEt.setHint("High School Name");
        highSchoolEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        highSchoolEt.setTextColor(getResources().getColor(R.color.white));
        highSchoolEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams highSchoolParams = params;
        highSchoolEt.setLayoutParams(highSchoolParams);

        EditText locationEt = new EditText(this);
        locationEt.setHint("City");
        locationEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        locationEt.setTextColor(getResources().getColor(R.color.white));
        locationEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams locationParams = params;
        locationParams.setMargins(0, 20, 0, 0);
        locationEt.setLayoutParams(locationParams);

        EditText stateEt = new EditText(this);
        stateEt.setHint("State");
        stateEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        stateEt.setTextColor(getResources().getColor(R.color.white));
        stateEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams stateParams = params;
        stateParams.setMargins(0, 20, 0, 0);
        stateEt.setLayoutParams(stateParams);

        Spinner spinner = new Spinner(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.degree_arrays, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setPrompt("Degree Acquired/Pursuing");
        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));
        LinearLayout.LayoutParams spinnerParams = params;
        spinnerParams.setMargins(0, 20, 0, 0);
        spinner.setLayoutParams(spinnerParams);

        final Button graduation = new Button(this);
        LinearLayout.LayoutParams graduationParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        graduation.setText("Date Degree Received");
        graduation.setTextColor(getResources().getColor(R.color.white));
        graduation.setBackground(getResources().getDrawable(R.drawable.button_login));
        graduationParams.setMargins(0, 20, 0, 0);
        graduation.setLayoutParams(graduationParams);

        final TextView graduationTV = new TextView(this);
        graduationTV.setTextColor(getResources().getColor(R.color.white));
        LinearLayout.LayoutParams graduationTVParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        graduationTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        graduationTVParams.setMargins(0, 10, 0, 0);
        graduationTV.setLayoutParams(graduationTVParams);

        startDate = Calendar.getInstance();

        /* add a click listener to the button   */
        graduation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(graduationTV, startDate);
            }
        });

        /* display the current date (this method is below)  */
        updateDisplay(graduationTV, startDate);

        currentStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStudent.isChecked()) {
                    graduation.setVisibility(View.GONE);
                    graduationTV.setVisibility(View.GONE);
                } else {
                    graduation.setVisibility(View.VISIBLE);
                    graduationTV.setVisibility(View.VISIBLE);
                }
            }
        });

        Button addButton = new Button(this);
        addButton.setText("+ Add Another High School");
        addButton.setTextColor(getResources().getColor(R.color.white));
        addButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        addButton.setPadding(0, 10, 0, 10);
        addButton.setBackgroundResource(R.drawable.add_position_button_style);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        addButton.setLayoutParams(buttonParams);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHighSchool(v);
            }
        });

        ((LinearLayout) linearLayout).addView(currentStudent);
        ((LinearLayout) linearLayout).addView(highSchoolEt);
        ((LinearLayout) linearLayout).addView(locationEt);
        ((LinearLayout) linearLayout).addView(stateEt);
        ((LinearLayout) linearLayout).addView(spinner);
        ((LinearLayout) linearLayout).addView(graduation);
        ((LinearLayout) linearLayout).addView(graduationTV);
        ((LinearLayout) linearLayout).addView(addButton);
    }

    public void addCollege(View v) {
        View linearLayout =  findViewById(R.id.collegeLayout);
        Button originalAddButton = (Button) findViewById(R.id.addCollegeButton);
        originalAddButton.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        final CheckBox currentStudent = new CheckBox(this);
        currentStudent.setText("Currently Attending");
        currentStudent.setTextColor(getResources().getColor(R.color.white));

        EditText universityEt = new EditText(this);
        universityEt.setHint("University Name");
        universityEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        universityEt.setTextColor(getResources().getColor(R.color.white));
        universityEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams universityParams = params;
        universityEt.setLayoutParams(universityParams);

        EditText locationEt = new EditText(this);
        locationEt.setHint("City");
        locationEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        locationEt.setTextColor(getResources().getColor(R.color.white));
        locationEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams locationParams = params;
        locationParams.setMargins(0, 20, 0, 0);
        locationEt.setLayoutParams(locationParams);

        EditText stateEt = new EditText(this);
        stateEt.setHint("State");
        stateEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        stateEt.setTextColor(getResources().getColor(R.color.white));
        stateEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams stateParams = params;
        stateParams.setMargins(0, 20, 0, 0);
        stateEt.setLayoutParams(stateParams);

        EditText majorEt = new EditText(this);
        majorEt.setHint("Major/Area of Study");
        majorEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        majorEt.setTextColor(getResources().getColor(R.color.white));
        majorEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams majorParams = params;
        majorParams.setMargins(0, 20, 0, 0);
        majorEt.setLayoutParams(majorParams);

        Spinner spinner = new Spinner(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.degree_arrays, R.layout.spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setPrompt("Degree Acquired/Pursuing");
        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));

        final Button graduation = new Button(this);
        LinearLayout.LayoutParams graduationParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        graduation.setText("Date Degree Received");
        graduation.setTextColor(getResources().getColor(R.color.white));
        graduation.setBackground(getResources().getDrawable(R.drawable.button_login));
        graduationParams.setMargins(0, 20, 0, 0);
        graduation.setLayoutParams(graduationParams);

        final TextView graduationTV = new TextView(this);
        graduationTV.setTextColor(getResources().getColor(R.color.white));
        LinearLayout.LayoutParams graduationTVParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        graduationTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        graduationTVParams.setMargins(0, 10, 0, 0);
        graduationTV.setLayoutParams(graduationTVParams);

        startDate = Calendar.getInstance();

        /* add a click listener to the button   */
        graduation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(graduationTV, startDate);
            }
        });

        /* display the current date (this method is below)  */
        updateDisplay(graduationTV, startDate);

        currentStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStudent.isChecked()) {
                    graduation.setVisibility(View.GONE);
                    graduationTV.setVisibility(View.GONE);
                } else {
                    graduation.setVisibility(View.VISIBLE);
                    graduationTV.setVisibility(View.VISIBLE);
                }
            }
        });

        Button addButton = new Button(this);
        addButton.setText("+ Add Another College");
        addButton.setTextColor(getResources().getColor(R.color.white));
        addButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        addButton.setPadding(0, 10, 0, 10);
        addButton.setBackgroundResource(R.drawable.add_position_button_style);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        addButton.setLayoutParams(buttonParams);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCollege(v);
            }
        });

        ((LinearLayout) linearLayout).addView(currentStudent);
        ((LinearLayout) linearLayout).addView(universityEt);
        ((LinearLayout) linearLayout).addView(locationEt);
        ((LinearLayout) linearLayout).addView(stateEt);
        ((LinearLayout) linearLayout).addView(majorEt);
        ((LinearLayout) linearLayout).addView(spinner);
        ((LinearLayout) linearLayout).addView(graduation);
        ((LinearLayout) linearLayout).addView(graduationTV);
        ((LinearLayout) linearLayout).addView(addButton);
    }

    public void addCertification(View v) {
        View linearLayout =  findViewById(R.id.certificationsLayout);
        Button originalAddButton = (Button) findViewById(R.id.addCertificationButton);
        originalAddButton.setVisibility(View.GONE);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout relativeLayout = new RelativeLayout(this);

        EditText certNameEt = new EditText(this);
        certNameEt.setHint("Certification Name");
        certNameEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        certNameEt.setTextColor(getResources().getColor(R.color.white));
        certNameEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        certNameEt.setLayoutParams(params);

        EditText certAuthorityEt = new EditText(this);
        certAuthorityEt.setHint("Certification Authority");
        certAuthorityEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        certAuthorityEt.setTextColor(getResources().getColor(R.color.white));
        certAuthorityEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        params.setMargins(0, 20, 0, 0);
        certAuthorityEt.setLayoutParams(params);

        EditText licenseNumberEt = new EditText(this);
        licenseNumberEt.setHint("License Number");
        licenseNumberEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        licenseNumberEt.setTextColor(getResources().getColor(R.color.white));
        licenseNumberEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        licenseNumberEt.setLayoutParams(params);

        TextView datesTv = new TextView(this);
        datesTv.setText("Dates");
        datesTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        datesTv.setTextColor(getResources().getColor(R.color.white));
        datesTv.setLayoutParams(params);
        relativeLayout.addView(datesTv);

        Button startDate = new Button(this);
        startDate.setText("Start Date");
        startDate.setId(1);
        RelativeLayout.LayoutParams startDateButtonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        startDate.setTextColor(getResources().getColor(R.color.white));
        startDate.setBackground(getResources().getDrawable(R.drawable.button_login));
        startDate.setPadding(0, 10, 0, 10);
        startDateButtonParams.setMargins(0, 60, 0, 0);
        startDate.setLayoutParams(startDateButtonParams);
        relativeLayout.addView(startDate);

        TextView startDateTV = new TextView(this);
        startDateTV.setTextColor(getResources().getColor(R.color.white));
        RelativeLayout.LayoutParams startDateTVParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        startDateTVParams.addRule(RelativeLayout.ALIGN_START, startDate.getId());
        startDateTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        startDateTVParams.setMargins(0, 150, 0, 0);
        startDateTV.setLayoutParams(startDateTVParams);
        relativeLayout.addView(startDateTV);

        final Button endDate = new Button(this);
        endDate.setText("End Date");
        endDate.setId(2);
        RelativeLayout.LayoutParams endDateButtonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        endDateButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        endDate.setTextColor(getResources().getColor(R.color.white));
        endDate.setBackground(getResources().getDrawable(R.drawable.button_login));
        endDateButtonParams.setMargins(0, 60, 0, 0);
        endDate.setLayoutParams(endDateButtonParams);
        relativeLayout.addView(endDate);

        final TextView endDateTV = new TextView(this);
        endDateTV.setTextColor(getResources().getColor(R.color.white));
        RelativeLayout.LayoutParams endDateTVParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        endDateTVParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        endDateTVParams.addRule(RelativeLayout.ALIGN_START, endDate.getId());
        endDateTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        endDateTVParams.setMargins(-10 , 150, 0, 0);
        endDateTV.setLayoutParams(endDateTVParams);
        relativeLayout.addView(endDateTV);

        newStartDate = Calendar.getInstance();
        newEndDate = Calendar.getInstance();

        startDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(startDateDisplay, newStartDate);
            }
        });

        endDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(endDateDisplay, newEndDate);
            }
        });

        updateDisplay(startDateTV, newStartDate);
        updateDisplay(endDateTV, newEndDate);

        final CheckBox noExpiration = new CheckBox(this);
        noExpiration.setText("No expiration date");
        noExpiration.setTextColor(getResources().getColor(R.color.white));

        noExpiration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noExpiration.isChecked()) {
                    endDate.setVisibility(View.GONE);
                    endDateTV.setVisibility(View.GONE);
                } else {
                    endDate.setVisibility(View.VISIBLE);
                    endDateTV.setVisibility(View.VISIBLE);
                }
            }
        });

        Button addButton = new Button(this);
        addButton.setText("+ Add Another Certification");
        addButton.setTextColor(getResources().getColor(R.color.white));
        addButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        addButton.setPadding(0, 10, 0, 10);
        addButton.setBackgroundResource(R.drawable.add_position_button_style);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        addButton.setLayoutParams(buttonParams);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCertification(v);
            }
        });

        ((LinearLayout) linearLayout).addView(certNameEt);
        ((LinearLayout) linearLayout).addView(certAuthorityEt);
        ((LinearLayout) linearLayout).addView(licenseNumberEt);
        ((LinearLayout) linearLayout).addView(relativeLayout);
        ((LinearLayout) linearLayout).addView(noExpiration);
        ((LinearLayout) linearLayout).addView(addButton);
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

    private void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                        .append(date.get(Calendar.YEAR)).append(" "));

    }

    public void showDateDialog(TextView dateDisplay, Calendar date) {
        activeDateDisplay = dateDisplay;
        activeDate = date;
        showDialog(DATE_DIALOG_ID);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            activeDate.set(Calendar.YEAR, year);
            activeDate.set(Calendar.MONTH, monthOfYear);
            activeDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(activeDateDisplay, activeDate);
            unregisterDateDisplay();
        }
    };


    private void unregisterDateDisplay() {
        activeDateDisplay = null;
        activeDate = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(activeDate.get(Calendar.YEAR), activeDate.get(Calendar.MONTH), activeDate.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }

    /*public void clickNext (View v) {
        Intent employee = new Intent(add_experience.this, add_education.class);
        startActivity(employee);
    }*/
}
