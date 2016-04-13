package com.avalanche2.a2passwordgenerator;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import java.util.Random;
import java.lang.reflect.Field;
//test

public class Generator extends AppCompatActivity {

    private String lowerCase = "abcdefghijklmnopqrstuvwyxz";
    private String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String digits = "0123456789";
    private String symbols = "~!@#$%^&*()_+=-";
    private Random rand = new Random();
    private String password = "";
    private String finalPassword = "";
    private int numberOfDigits = 1;



    /**
     * Sets the number picker's text to white.
     * @param numberPicker
     * @param color
     * @return
     */
    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color)
    {
        final int count = numberPicker.getChildCount();
        for(int i = 0; i < count; i++){
            View child = numberPicker.getChildAt(i);
            if(child instanceof EditText){
                try{
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint)selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText)child).setTextColor(color);
                    numberPicker.invalidate();
                    return true;
                }
                catch(NoSuchFieldException e){
                    Log.w("setNumberPickerTextColo", e);
                }
                catch(IllegalAccessException e){
                    Log.w("setNumberPickerTextColr", e);
                }
                catch(IllegalArgumentException e){
                    Log.w("setNumberPickerTextColo", e);
                }
            }
        }
        return false;
    }

    private void setDividerColor(NumberPicker picker, int color) {

        java.lang.reflect.Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (java.lang.reflect.Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    ColorDrawable colorDrawable = new ColorDrawable(color);
                    pf.set(picker, colorDrawable);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public void fillPass(String password1, int digits){
        char[] charPass = password1.toCharArray();
        char[] charPass2 = new char[digits];
        for(int i = 0; i < digits; i++){
            charPass2[i] = charPass[rand.nextInt(charPass.length)];
        }
        finalPassword = new String(charPass2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        final NumberPicker myPicker = (NumberPicker) findViewById(R.id.andyPicker);

        final CheckBox check1 = (CheckBox) findViewById(R.id.checkBox);
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox2);
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        final CheckBox check3 = (CheckBox) findViewById(R.id.checkBox3);
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        final CheckBox check4 = (CheckBox) findViewById(R.id.checkBox4);
        check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


        myPicker.setMinValue(4);
        myPicker.setMaxValue(10);
        myPicker.setWrapSelectorWheel(false);
        setNumberPickerTextColor(myPicker, -1);
        setDividerColor(myPicker, Color.rgb(231, 188, 235));



        final Button button = (Button) findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                if(check1.isChecked() || check2.isChecked() || check3.isChecked() || check4.isChecked()) {
                    password = "";
                    numberOfDigits = myPicker.getValue();
                    if (check1.isChecked()) {
                        password = password.concat(upperCase);
                    }
                    if (check2.isChecked()) {
                        password = password.concat(digits);
                    }
                    if (check3.isChecked()) {
                        password = password.concat(lowerCase);
                    }
                    if (check4.isChecked()) {
                        password = password.concat(symbols);
                    }
                    fillPass(password, numberOfDigits);
                    ((TextView) findViewById(R.id.editText)).setText(finalPassword);
                } else {
                    ((TextView) findViewById(R.id.editText)).setText("Password");
                }
            }
        });

    }
}
