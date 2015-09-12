package com.example.ajindal1.foodforfavors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {
    public static final String TAG = WelcomeActivity.class.getSimpleName();
    protected Button logout;
    ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = (Button)findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.logOut();
                currentUser = ParseUser.getCurrentUser();
                goToLoginMenu();
            }
        });

        currentUser = ParseUser.getCurrentUser();
        if(currentUser == null) {
            goToLoginMenu();
        }
        else {
            Log.i(TAG,currentUser.getUsername());
        }

    }

    private void goToLoginMenu() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
