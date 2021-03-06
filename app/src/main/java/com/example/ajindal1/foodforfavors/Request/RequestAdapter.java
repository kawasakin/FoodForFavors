package com.example.ajindal1.foodforfavors.Request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ajindal1.foodforfavors.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ajindal1 on 9/12/15.
 */
public class RequestAdapter extends ArrayAdapter<Request>
{
    public RequestAdapter(Context context, ArrayList<Request> requests) {
        super(context, 0, requests);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Request request = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_request, parent, false);
        }
        // Lookup view for data population
        TextView uNameTextView = (TextView) convertView.findViewById(R.id.usernameTextView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextview);
        ImageView image = (ImageView) convertView.findViewById(R.id.requestImage);
        TextView dateView = (TextView) convertView.findViewById(R.id.dateTextView);
        // Populate the data into the template view using the data object

        Date date = request.getDate();
        if(date != null) {
            Date now = new Date();
            long seconds = (now.getTime() - date.getTime())/1000;
            long minutes = seconds/60;
            long hours = minutes/60;
            long days = hours/24;
            if(days != 0)
                dateView.setText("" + days + " day(s) ago");
            else if (hours != 0)
                dateView.setText("" + hours + " hour(s) ago");
            else if (minutes != 0)
                dateView.setText("" + minutes + " minute(s) ago");
            else if (seconds >= 30)
                dateView.setText("" + seconds + " second(s) ago");
            else
                dateView.setText("Just now");
        }
        image.setImageDrawable(convertView.getResources().getDrawable(request.getImageId()));
        messageTextView.setText(request.getMessage());
        uNameTextView.setText(request.getUsername());



        // Return the completed view to render on screen
        return convertView;
    }

}
