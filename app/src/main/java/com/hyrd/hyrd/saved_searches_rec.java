package com.hyrd.hyrd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class saved_searches_rec extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Ubuntu-R.ttf");
        setContentView(R.layout.activity_saved_searches_rec);

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
        displaySearches();
    }

    public void displaySearches() {
        View linearLayout = findViewById(R.id.savedSearchesList);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView noSearches = new TextView(this);
        noSearches.setText("You have no saved searches.");
        noSearches.setTextColor(getResources().getColor(R.color.white));
        noSearches.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        params.gravity = Gravity.CENTER;
        noSearches.setLayoutParams(params);

        Button searchButton = new Button(this);
        searchButton.setText("Go search now!");
        searchButton.setTextColor(getResources().getColor(R.color.white));
        searchButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        searchButton.setBackgroundResource(R.drawable.button_login);
        searchButton.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        searchButton.setLayoutParams(buttonParams);
        buttonParams.setMargins(0, 50, 0, 0);
        buttonParams.gravity = Gravity.CENTER;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(saved_searches_rec.this, search_page_rec.class);
                startActivity(search);
            }
        });

        ((LinearLayout) linearLayout).addView(noSearches);
        ((LinearLayout) linearLayout).addView(searchButton);
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
