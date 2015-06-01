package com.hyrd.hyrd;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class forgot_password extends ActionBarActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

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

        final Button send = (Button) this.findViewById(R.id.forgotPassButton);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    GMailSender sender = new GMailSender("m.shafique27@gmail.com", "contact4");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "m.shafique27@gmail.com",
                            "m.shafique27@gmail.com");
                    Log.d("SendMail", "Email Sent");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

            }
        });

    }
}

/*import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class forgot_password extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        final Button send = (Button) this.findViewById(R.id.forgotPassButton);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    GMailSender sender = new GMailSender("m.shafique27@gmail.com", "contact4");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "m.shafique27@gmail.com",
                            "mshafiqu@ucsc.edu");
                    Log.d("SendMail", "Email Sent");
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forgot_password, menu);
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
}*/
