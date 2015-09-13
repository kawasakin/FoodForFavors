package com.example.ajindal1.foodforfavors;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ajindal1.foodforfavors.Request.Request;
import com.example.ajindal1.foodforfavors.Request.RequestAdapter;
import com.parse.FindCallback;
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
    ArrayList<Request> requests;
    RequestAdapter adapter;
    ListView listView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_request_list,
                container, false);

        return rootView;
    }


    public void onResume() {
        super.onResume();

        requests = new ArrayList<Request>();
        adapter = new RequestAdapter(getContext(), requests);
        listView = (ListView) getView().findViewById(R.id.requestList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Request item = (Request)listView.getItemAtPosition(position);
                Log.e("RequestFragment", item.getDate().toString());
                Intent intent = new Intent(getContext(),connect.class);
                intent.putExtra("Date",item.getDate());
                intent.putExtra("ImageId",item.getImageId());
                intent.putExtra("Message",item.getMessage());
                intent.putExtra("Username",item.getUsername());
                intent.putExtra("ParseUserId",item.getParseUser().getObjectId());

                startActivity(intent);

            }
        });


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Request");
        query.orderByDescending("createdAt");
        query.setLimit(100);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                //Toast.makeText(getContext(), "Do i go here", Toast.LENGTH_SHORT).show();
                mRequests = list;


                for (ParseObject o : list) {

                    ParseUser user = (ParseUser) o.get("User");
                    Log.e("RequestFragment", user.getObjectId());
                    try {
                        user.fetchIfNeeded();
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                    String username = user.getUsername();
                    String message = (String) o.get("RequestText");
                    Date date = o.getCreatedAt();
                    int imageId = o.getInt("ImageId");
                    adapter.add(new Request(user, username, message, imageId, date));
                }
            }

        });

    }




}
