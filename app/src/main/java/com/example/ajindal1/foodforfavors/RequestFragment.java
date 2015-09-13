package com.example.ajindal1.foodforfavors;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ajindal1.foodforfavors.Request.Request;
import com.example.ajindal1.foodforfavors.Request.RequestAdapter;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link FavorFragment.OnFragmentInteractionListener}
 * interface.
 */
public class RequestFragment extends Fragment {

    List<ParseObject> mRequests;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_request_list,
                container, false);

        return rootView;
    }


    public void onResume() {
        super.onResume();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Request");
        query.orderByDescending("createdAt");
        query.setLimit(100);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                //Toast.makeText(getContext(), "Do i go here", Toast.LENGTH_SHORT).show();
                mRequests = list;

                ArrayList<Request> requests = new ArrayList<Request>();
                // Create the adapter to convert the array to views
                RequestAdapter adapter = new RequestAdapter(getContext(), requests);
                // Attach the adapter to a ListView
                ListView listView = (ListView) getView().findViewById(R.id.requestList);
                listView.setAdapter(adapter);


                for (ParseObject o : list) {

                    ParseUser user = (ParseUser) o.get("User");
                    Log.e("RequestFragment",user.getObjectId());
                    try {
                        user.fetchIfNeeded();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    String username =  user.getUsername();
                    String message = (String) o.get("RequestText");
                    Date date = o.getCreatedAt();
                    int imageId = o.getInt("ImageId");
                    adapter.add(new Request(user, username, message, imageId, date));

                }


            }

        });

    }




}
