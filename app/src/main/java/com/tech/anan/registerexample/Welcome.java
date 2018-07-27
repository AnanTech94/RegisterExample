package com.tech.anan.registerexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView email, pass, mobile;
    CheckBox passShowHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        email = (TextView) findViewById(R.id.userEmail);
        pass = (TextView) findViewById(R.id.userPass);
        mobile = (TextView) findViewById(R.id.userMobile);

        String userEmail = getIntent().getStringExtra("USER_EMAIL");
        String userPass = getIntent().getStringExtra("USER_PASS");
        String userMobile = getIntent().getStringExtra("USER_MOBILE");

        email.setText(userEmail);
        pass.setText(userPass);
        pass.setTransformationMethod(new PasswordTransformationMethod());
        mobile.setText(userMobile);

        passShowHide = (CheckBox) findViewById(R.id.passShowHide);


        passShowHide.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

    }
}
