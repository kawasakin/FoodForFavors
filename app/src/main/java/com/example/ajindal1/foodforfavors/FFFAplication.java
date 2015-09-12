package com.example.ajindal1.foodforfavors;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by ajindal1 on 9/12/15.
 */
public class FFFAplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "QtXHm44cOU25ccp6AwARDszU5YRIojE9dvLQ8ZDN", "nOnSNzPABCYf8Y258gg0ETpO0yGC0JjG53L2wD2u");


    }

}
