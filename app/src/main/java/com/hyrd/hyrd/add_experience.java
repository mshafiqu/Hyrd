package com.hyrd.hyrd;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;


public class add_experience extends ActionBarActivity {

    private int count = 1;

    public static final String[] MONTHS = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

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

    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experience);

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

        /*mStartDateDisplay = (TextView) findViewById(R.id.startDate);
        mPickStartDate = (Button) findViewById(R.id.startDateButton);

        mEndDateDisplay = (TextView) findViewById(R.id.endDate);
        mPickEndDate = (Button) findViewById(R.id.endDateButton);

        mPickStartDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_START_DIALOG_ID);
            }
        });

        mPickEndDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_END_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mStartYear = c.get(Calendar.YEAR);
        mStartMonth = c.get(Calendar.MONTH);
        mStartDay = c.get(Calendar.DAY_OF_MONTH);
        mEndYear = c.get(Calendar.YEAR);
        mEndMonth = c.get(Calendar.MONTH);
        mEndDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        updateStartDisplay(mStartDateDisplay);
        updateEndDisplay(mEndDateDisplay);*/

        /*  capture our View elements for the start date function   */
        startDateDisplay = (TextView) findViewById(R.id.startDate);
        startPickDate = (Button) findViewById(R.id.startDateButton);

        /* get the current date */
        startDate = Calendar.getInstance();

        /* add a click listener to the button   */
        startPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(startDateDisplay, startDate);
            }
        });

        /* capture our View elements for the end date function */
        endDateDisplay = (TextView) findViewById(R.id.endDate);
        endPickDate = (Button) findViewById(R.id.endDateButton);

        /* get the current date */
        endDate = Calendar.getInstance();

        /* add a click listener to the button   */
        endPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(endDateDisplay, endDate);
            }
        });

        /* display the current date (this method is below)  */
        updateDisplay(startDateDisplay, startDate);
        updateDisplay(endDateDisplay, endDate);
    }

    public void addExperience (View v) {
        count++;

        Button originalAddButton = (Button) findViewById(R.id.newPositionButton);
        originalAddButton.setVisibility(View.GONE);

        View linearLayout =  findViewById(R.id.layoutEmp);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        RelativeLayout relativeLayout =  new RelativeLayout(this);

        TextView newTV = new TextView(this);
        newTV.setText("Position " + count);
        newTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        newTV.setTextColor(getResources().getColor(R.color.gold));
        newTV.setLayoutParams(params);

        EditText companyEt = new EditText(this);
        companyEt.setHint("Company");
        companyEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        companyEt.setTextColor(getResources().getColor(R.color.white));
        companyEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams companyParams = params;
        companyParams.setMargins(0, 20, 0, 0);
        companyEt.setLayoutParams(companyParams);

        EditText positionEt = new EditText(this);
        positionEt.setHint("Position");
        positionEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        positionEt.setTextColor(getResources().getColor(R.color.white));
        positionEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams positionParams = params;
        positionParams.setMargins(0, 20, 0, 0);
        positionEt.setLayoutParams(positionParams);

        EditText locationEt = new EditText(this);
        locationEt.setHint("Location");
        locationEt.setHintTextColor(getResources().getColor(R.color.hintColor));
        locationEt.setTextColor(getResources().getColor(R.color.white));
        locationEt.getBackground().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        LinearLayout.LayoutParams locationParams = params;
        locationParams.setMargins(0, 20, 0, 0);
        locationEt.setLayoutParams(locationParams);

        Button startDate = new Button(this);
        startDate.setText("Start Date");
        startDate.setId(1);
        startDate.setTextColor(getResources().getColor(R.color.white));
        startDate.setBackground(getResources().getDrawable(R.drawable.button_login));
        RelativeLayout.LayoutParams startDateParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        startDateParams.setMargins(0, 15, 0, 0);
        startDate.setPadding(0, 10, 0, 10);
        startDate.setLayoutParams(startDateParams);
        relativeLayout.addView(startDate);

        TextView startDateTV = new TextView(this);
        RelativeLayout.LayoutParams startDateTVParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        startDateTVParams.addRule(RelativeLayout.ALIGN_START, startDate.getId());
        startDateTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        startDateTV.setTextColor(getResources().getColor(R.color.white));
        startDateTVParams.setMargins(0, 105, 0, 0);
        startDateTV.setLayoutParams(startDateTVParams);
        relativeLayout.addView(startDateTV);

        Button endDate = new Button(this);
        endDate.setText("End Date");
        endDate.setId(2);
        RelativeLayout.LayoutParams endDateButtonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        endDateButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        endDate.setTextColor(getResources().getColor(R.color.white));
        endDate.setBackground(getResources().getDrawable(R.drawable.button_login));
        endDateButtonParams.setMargins(0, 15, 0, 0);
        endDate.setLayoutParams(endDateButtonParams);
        relativeLayout.addView(endDate);

        TextView endDateTV = new TextView(this);
        endDateTV.setTextColor(getResources().getColor(R.color.white));
        RelativeLayout.LayoutParams endDateTVParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        endDateTVParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        endDateTVParams.addRule(RelativeLayout.ALIGN_START, endDate.getId());
        endDateTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        endDateTVParams.setMargins(-10, 105, 0, 0);
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

        EditText textbox = new EditText(this);
        textbox.setHint("Job Description");
        textbox.setSingleLine(false);
        textbox.setMinLines(5);
        textbox.setMaxLines(5);
        textbox.setHintTextColor(getResources().getColor(R.color.hintColor));
        textbox.setTextColor(getResources().getColor(R.color.white));
        textbox.setBackgroundResource(R.drawable.description_textbox);
        textbox.setInputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES | InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        textbox.setHorizontallyScrolling(false);
        textbox.setGravity(Gravity.TOP);
        LinearLayout.LayoutParams textboxParams = params;
        textboxParams.setMargins(0, 20, 0, 0);
        textbox.setLayoutParams(textboxParams);

        addButton = new Button(this);
        addButton.setText("+ Add Another Position");
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
                addButton.setVisibility(View.GONE);
                addExperience(v);
            }
        });

        ((LinearLayout) linearLayout).addView(newTV);
        ((LinearLayout) linearLayout).addView(companyEt);
        ((LinearLayout) linearLayout).addView(positionEt);
        ((LinearLayout) linearLayout).addView(locationEt);
        ((LinearLayout) linearLayout).addView(relativeLayout);
        ((LinearLayout) linearLayout).addView(textbox);
        ((LinearLayout) linearLayout).addView(addButton);
    }

    View.OnClickListener handleOnClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                addExperience(v);
            }
        };
    }

    /*private void updateStartDisplay(TextView startDateTV) {
        startDateTV.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(MONTHS[mStartMonth]).append("-")
                        .append(mStartYear).append(" "));
    }

    private void updateEndDisplay(TextView endDateTV) {
        endDateTV.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(MONTHS[mEndMonth]).append("-")
                        .append(mEndYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mStartDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mStartYear = year;
                    mStartMonth = monthOfYear;
                    mStartDay = dayOfMonth;
                    updateStartDisplay(mStartDateDisplay);
                }
            };

    private DatePickerDialog.OnDateSetListener mEndDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mEndYear = year;
                    mEndMonth = monthOfYear;
                    mEndDay = dayOfMonth;
                    updateEndDisplay(mEndDateDisplay);
                }
            };*/

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

    /*@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_START_DIALOG_ID:
                return new DatePickerDialog(this,
                        mStartDateSetListener,
                        mStartYear, mStartMonth, mStartDay);
            case DATE_END_DIALOG_ID:
                return new DatePickerDialog(this,
                        mEndDateSetListener,
                        mEndYear, mEndMonth, mEndDay);
        }
        return null;
    }*/

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

    private OnDateSetListener dateSetListener = new OnDateSetListener() {
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

    public void clickNext (View v) {
        Intent employee = new Intent(add_experience.this, add_education.class);
        startActivity(employee);
    }

}
