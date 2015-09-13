package com.example.ajindal1.foodforfavors.Request;

import android.os.Parcelable;

import com.parse.ParseUser;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ajindal1 on 9/12/15.
 */
@SuppressWarnings("serial")
public class Request
{
    public ParseUser getParseUser() {
        return mParseUser;
    }

    public void setParseUser(ParseUser mParseUser) {
        this.mParseUser = mParseUser;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public int getImageId() {
        return mimageId;
    }

    public void setImageId(int mimageId) {
        this.mimageId = mimageId;
    }

    ParseUser mParseUser;
    String mUsername,mMessage;
    int mimageId;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    Date mDate;

    public Request(ParseUser parseUser, String username, String message, int imageId , Date date) {
        mParseUser = parseUser;
        mUsername = username;
        mMessage = message;
        mimageId = imageId;
        mDate = date;
    }
}
