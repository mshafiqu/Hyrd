package com.hyrd.hyrd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;


public class home_page_rec extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Ubuntu-R.ttf");
        setContentView(R.layout.activity_home_page_rec);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
    }

    public void searchPage(View v) {
        Intent search = new Intent(home_page_rec.this, search_page_rec.class);
        startActivity(search);
    }

    public void savedSearchesPage(View v) {
        Intent search = new Intent(home_page_rec.this, saved_searches_rec.class);
        startActivity(search);
    }

    public void starredRecruitsPage(View v) {
        Intent search = new Intent(home_page_rec.this, starred_recruits.class);
        startActivity(search);
    }

    public void accountSettingsPage(View v) {
        Intent search = new Intent(home_page_rec.this, profile_view.class);
        startActivity(search);
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
