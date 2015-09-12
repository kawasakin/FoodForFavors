package com.example.ajindal1.foodforfavors;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this)
                        .setMessage("Please make sure you enter a username, password, and e-mail address")
                        .setTitle("Oops!")
                        .setPositiveButton(android.R.string.ok,null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                else
                {
                    ParseUser newUser = new ParseUser();
                    newUser.setUsername(username);
                    newUser.setPassword(password);
                    newUser.setEmail(email);
                    newUser.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null) {
                                Intent intent = new Intent(SignUpActivity.this,WelcomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this)
                                        .setMessage(e.getMessage())
                                        .setTitle("Oops!")
                                        .setPositiveButton(android.R.string.ok,null);
                                AlertDialog dialog = builder.create();
                                dialog.show();

                            }
                        }
                    });
                }
            }
        });

    }

}
