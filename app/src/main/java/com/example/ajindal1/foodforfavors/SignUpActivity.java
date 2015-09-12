package com.example.ajindal1.foodforfavors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity
{

    protected EditText mUsername,mPassword,mEmail;
    protected Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername = (EditText)findViewById((R.id.signUpUsername));
        mPassword = (EditText)findViewById((R.id.signUpPassword));
        mEmail = (EditText)findViewById((R.id.signUpEmail));
        mSignUpButton = (Button)findViewById(R.id.signUpSubmit);

        mSignUpButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty() || email.isEmpty())
                {

                }
                else
                {

                }
            }
        });

    }

}
