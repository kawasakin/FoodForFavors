package com.example.ajindal1.foodforfavors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
    public static final String TAG = WelcomeActivity.class.getSimpleName();
    protected Button logout,mContinueButton;
    protected TextView welcomeMessage;
    ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeMessage = (TextView)findViewById(R.id.welcomeView);

        logout = (Button)findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.logOut();
                currentUser = ParseUser.getCurrentUser();
                goToLoginMenu();
            }
        });

        mContinueButton = (Button)findViewById(R.id.continueButton);
        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        currentUser = ParseUser.getCurrentUser();
        if(currentUser == null) {
            goToLoginMenu();
        }
        else {
            Log.i(TAG,currentUser.getUsername());
            welcomeMessage.setText("Hello " + currentUser.getUsername() + "! Welcome to Food For Favors!");
        }

    }

    private void goToLoginMenu() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
