package com.tech.anan.registerexample;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText email, pass, again, mobile;
    Button continueButton;
    LinearLayout emailContainer, passContainer, againContainer, mobContainer, input;
    FrameLayout emailFrame, passFrame, againFrame;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mob);
        pass = (EditText) findViewById(R.id.pass);
        again = (EditText) findViewById(R.id.again);

        emailContainer = (LinearLayout) findViewById(R.id.emailContainer);
        againContainer = (LinearLayout) findViewById(R.id.againContainer);
        passContainer = (LinearLayout) findViewById(R.id.passContainer);
        mobContainer = (LinearLayout) findViewById(R.id.mobContainer);
        input = (LinearLayout) findViewById(R.id.input);

        emailFrame = (FrameLayout) findViewById(R.id.emailFrame);
        againFrame = (FrameLayout) findViewById(R.id.againFrame);
        passFrame = (FrameLayout) findViewById(R.id.passFrame);

        continueButton = (Button) findViewById(R.id.cont);

        clearAll(input);

        continueButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                String userEmail = email.getText().toString();
                String userMobile = mobile.getText().toString();
                String userPass = pass.getText().toString();
                String userAgain = again.getText().toString();

                clearAll(input);

                if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches() || userEmail == null) {
                    Toast.makeText(getApplicationContext(), "Not Valid Email Address", Toast.LENGTH_LONG).show();
                    error(emailContainer);
                } else if (userPass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password Is Too Short,\nEnter At Least 6 Characters", Toast.LENGTH_LONG).show();
                    error(passContainer);
                } else if (!userAgain.equals(userPass)) {
                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
                    error(againContainer);
                } else if (!Patterns.PHONE.matcher(userMobile).matches() || userMobile == null) {
                    Toast.makeText(getApplicationContext(), "Not Valid Mobil Number", Toast.LENGTH_LONG).show();
                    error(mobContainer);
                } else {
                    db = new DatabaseHandler(MainActivity.this, null, null, 2);
                    User user = new User();

                    user.setEmail(userEmail);
                    user.setMobile(userMobile);
                    user.setPassword(userPass);
                    db.addregister(user);

                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, Welcome.class);
                    intent.putExtra("USER_EMAIL", userEmail);
                    intent.putExtra("USER_PASS", userPass);
                    intent.putExtra("USER_MOBILE", userMobile);
                    startActivity(intent);

                }

            }
        });


    }

    protected void error(LinearLayout inputError) {
        for (int i = 0, count = inputError.getChildCount(); i < count; ++i) {
            View view = inputError.getChildAt(i);
            if (view instanceof FrameLayout) {
                ((FrameLayout) view).setBackgroundColor(Color.parseColor("#FF412526"));
            }
            if (view instanceof EditText) {
                ((EditText) view).setBackgroundColor(Color.parseColor("#FF6E3F40"));
                ((EditText) view).selectAll();
                ((EditText) view).requestFocus();
            }
        }


    }

    protected void clearAll(ViewGroup input) {
        for (int i = 0, count = input.getChildCount(); i < count; ++i) {
            View view = input.getChildAt(i);
            if (view instanceof ViewGroup && !(view instanceof FrameLayout)) {
                clearAll((ViewGroup) view);
            } else if (view instanceof FrameLayout) {
                ((FrameLayout) view).setBackgroundColor(Color.parseColor("#555c5b"));
            } else if (view instanceof EditText) {
                ((EditText) view).setBackgroundColor(Color.parseColor("#626562"));
                //((EditText) view).setText("");
            }


        }

    }

}