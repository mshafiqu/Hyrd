package com.hyrd.hyrd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class search_page_rec extends ActionBarActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private int count = 1;
    private ArrayList<String> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Ubuntu-R.ttf");
        setContentView(R.layout.activity_search_page_rec);

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

        String[] skills_array = getResources().getStringArray(R.array.skills_arrays);
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteSkills);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,skills_array);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                if (count >3) {
                    Log.e("AutoCompleteTextView","More than 3 items selected");
                }else {
                    count++;
                    String selection = (String) parent.getItemAtPosition(position);
                    addSkillsTextView(selection);
                }
            }
        });
    }

    public void addSkillsTextView(final String selection) {
        View linearLayout =  findViewById(R.id.skillsLayout);
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

        final float scale = getResources().getDisplayMetrics().density;
        int height = (int) (1 * scale + 0.5f);
        int width = (int) (2 * scale + 0.5f);
        final View space = new View(this);
        LinearLayout.LayoutParams spaceParams = new LinearLayout.LayoutParams(
                width,
                height);
        space.setLayoutParams(spaceParams);

        autoCompleteTextView.setText("");
        selectedItems.add(selection);

        ((LinearLayout) linearLayout).addView(newTV);
        ((LinearLayout) linearLayout).addView(space);

        newTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(search_page_rec.this);
                builder.setMessage("Are you sure you want to delete this skill?");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                newTV.setVisibility(View.GONE);
                                space.setVisibility(View.GONE);
                                autoCompleteTextView.setEnabled(true);
                                selectedItems.remove(selection);
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
        if (count > 3) {
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
}
