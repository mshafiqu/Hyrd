package com.hyrd.hyrd;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/Ubuntu-R.ttf");
        setContentView(R.layout.activity_main);

        /*Typeface myTypeFaceTitle = Typeface.createFromAsset(getAssets(), "fonts/SignPainter-HouseScript.ttf");
        TextView loginTitle = (TextView) findViewById(R.id.loginTitle);
        loginTitle.setTypeface(myTypeFaceTitle);*/

        EditText passwordText = (EditText) findViewById(R.id.password);
        passwordText.setTransformationMethod(new PasswordTransformationMethod());
    }

    public void homeRec(View v) {
        Intent recruiter = new Intent(MainActivity.this, home_page_rec.class);
        startActivity(recruiter);
    }

    public void clickNewUser(View v) {
        final CharSequence[] items = {"Recruiter", "Potential Employee"};
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Who are you?");
        dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        Intent recruiter = new Intent(MainActivity.this, new_recruiter.class);
                        startActivity(recruiter);
                        break;
                    case 1:
                        Intent employee = new Intent(MainActivity.this, new_employee.class);
                        startActivity(employee);
                        break;
                }
            }
        });
        dialogBuilder.create().show();
    }

    public void forgotPassword(View v) {
        Intent password = new Intent(MainActivity.this, forgot_password.class);
        startActivity(password);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
