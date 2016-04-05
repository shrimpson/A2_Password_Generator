package com.avalanche2.a2passwordgenerator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class Generator extends AppCompatActivity {

    private char[] myChars = "0123456789abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private char[] password = new char[8];
    private Random rand = new Random();
    private String thePassword;

    public void fillPass(){
        for(int i = 0; i < 8; i++){
            password[i] = myChars[rand.nextInt(myChars.length)];
        }
        thePassword = new String(password);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        final Button button = (Button) findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                fillPass();
                ((TextView) findViewById(R.id.editText)).setText(thePassword);
            }
        });

    }
}
