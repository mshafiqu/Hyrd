package com.hyrd.hyrd;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soundcloud.android.crop.Crop;

import java.io.File;

public class upload_profile_pic extends ActionBarActivity {

    private ImageView resultView;
    private Boolean firstClick = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_pic);
        resultView = (ImageView) findViewById(R.id.result_image);

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

    public void nextPage(View v) {
        Intent current = this.getIntent();
        String strdata = current.getExtras().getString("Source");
        if (strdata.equals("from Recruiter")) {
            Intent recruiter = new Intent(upload_profile_pic.this, home_page_rec.class);
            startActivity(recruiter);
        } else if (strdata.equals("from Employee")) {
            Intent employee = new Intent(upload_profile_pic.this, add_experience.class);
            startActivity(employee);
        }
    }

    public void clickSkip(View v) {
        Intent current = this.getIntent();
        String strdata = current.getExtras().getString("Source");
        if (strdata.equals("from Recruiter")) {
            Intent recruiter = new Intent(upload_profile_pic.this, home_page_rec.class);
            startActivity(recruiter);
        } else if (strdata.equals("from Employee")) {
            Intent employee = new Intent(upload_profile_pic.this, add_experience.class);
            startActivity(employee);
        }
    }

    public void selectPhoto(View v) {
        resultView.setImageDrawable(null);
        Crop.pickImage(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).withMaxSize(800,800).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            resultView.setImageURI(Crop.getOutput(result));
            resultView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    selectPhoto(v);
                }
            });
            View linearLayout =  findViewById(R.id.LinearLayout1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 20, 0, 0);
            if (firstClick) {
                TextView newTV = new TextView(this);
                newTV.setText("Click on the picture to select a different photo.");
                newTV.setTextColor(getResources().getColor(R.color.white));
                newTV.setLayoutParams(params);
                newTV.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                newTV.setGravity(Gravity.CENTER);
                ((LinearLayout) linearLayout).addView(newTV);
                firstClick = false;
            }
            Button continueButton = (Button) findViewById(R.id.btnSelectPhoto);
            continueButton.setText("Continue");
            continueButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nextPage(v);
                }
            });
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

