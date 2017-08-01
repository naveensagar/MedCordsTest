package com.medcords.test.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView welcomeTextView = (TextView) findViewById(R.id.welcome_message);
        welcomeTextView.setText("Hello Naveen Sagar");


    }
}
