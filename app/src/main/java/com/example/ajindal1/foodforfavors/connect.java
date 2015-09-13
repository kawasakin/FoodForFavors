package com.example.ajindal1.foodforfavors;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ajindal1.foodforfavors.R;
import com.example.ajindal1.foodforfavors.Request.Request;
import com.parse.ParseUser;

public class connect extends AppCompatActivity {

    ParseUser mUser;
    TextView rName,rMessage;
    Button accept,counter;
    EditText counterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        Intent intent = getIntent();
        String parseID = intent.getStringExtra("ParseUserId");
        String username = intent.getStringExtra("Username");
        String message = intent.getStringExtra("Message");

        mUser = ParseUser.getCurrentUser();
        if(mUser.getObjectId().equals(parseID)) {
            Toast.makeText(connect.this, "You cant do your own job!", Toast.LENGTH_SHORT).show();
            finish();
        }

        rName = (TextView)findViewById(R.id.requestorView);
        rMessage = (TextView)findViewById(R.id.messageView);
        accept = (Button)findViewById(R.id.acceptButton);
        counter = (Button)findViewById(R.id.counterButton);
        counterText = (EditText)findViewById(R.id.counterText);

        rName.setText(username + " needs your help!:");
        rMessage.setText(message);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counterText.getText().toString().length()!= 0)
                    finish();
            }
        });


        counterText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(counterText.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_connect, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
