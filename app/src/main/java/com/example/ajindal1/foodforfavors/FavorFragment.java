package com.example.ajindal1.foodforfavors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.w3c.dom.Text;

import imagedeck.FoodItem;
import imagedeck.ImageDeck;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FavorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavorFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View mView;

    protected ImageButton lb,rb;
    protected Button request;
    protected Image foodImage;
    protected EditText editText,submitText;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavorFragment newInstance(String param1, String param2) {
        FavorFragment fragment = new FavorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FavorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView =  inflater.inflate(R.layout.fragment_favor, container, false);

        // Inflate the layout for this fragment
        final ImageButton lb = (ImageButton)mView.findViewById(R.id.lButton);
        final ImageButton rb = (ImageButton)mView.findViewById(R.id.rButton);
        final Button request = (Button)mView.findViewById(R.id.request);
        final ImageView foodImage = (ImageView)mView.findViewById(R.id.requestImage);
        final EditText editText = (EditText)mView.findViewById(R.id.requestField);
        final EditText submitText = (EditText)mView.findViewById(R.id.submitText);
        final TextView textview2 = (TextView)mView.findViewById(R.id.textView2);
        final ImageDeck deck = new ImageDeck();

        foodImage.setImageDrawable(getResources().getDrawable(deck.getCurrent().getImageID()));


        foodImage.setVisibility(View.INVISIBLE);
        submitText.setVisibility(View.INVISIBLE);
        request.setVisibility(View.INVISIBLE);
        lb.setVisibility(View.INVISIBLE);
        rb.setVisibility(View.INVISIBLE);
        textview2.setVisibility(View.INVISIBLE);



        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {


                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {


                    // Perform action on key press
                    //Toast.makeText(getContext(), editText.getText(), Toast.LENGTH_SHORT).show();
                    foodImage.setVisibility(View.VISIBLE);
                    submitText.setVisibility(View.VISIBLE);
                    request.setVisibility(View.VISIBLE);
                    lb.setVisibility(View.VISIBLE);
                    rb.setVisibility(View.VISIBLE);
                    textview2.setVisibility(View.VISIBLE);

                    FoodItem item = deck.getCurrent();
                    foodImage.setImageDrawable(getResources().getDrawable(item.getImageID()));
                    String text = "I need " + editText.getText().toString()
                            + " and I am willing to offer " + item.getName()
                            + "(" + item.getQuantity() + ").";
                    submitText.setText(text);

                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);


                    return true;
                }
                return false;
            }

        });



        submitText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(submitText.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });

        lb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodItem item = deck.moveLeft();
                foodImage.setImageDrawable(getResources().getDrawable(item.getImageID()));
                String text = "I need " + editText.getText().toString()
                        + " and I am willing to offer " + item.getName()
                        + "(" + item.getQuantity() + ").";
                submitText.setText(text);

            }
        });

        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FoodItem item = deck.moveRight();
                foodImage.setImageDrawable(getResources().getDrawable(item.getImageID()));
                String text = "I need someone to " + editText.getText().toString()
                        + " and I am willing to offer " + item.getName()
                        + "(" + item.getQuantity() + ").";
                submitText.setText(text);
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject newRequest = new ParseObject("Request");
                newRequest.put("RequestText",submitText.getText().toString());
                newRequest.put("ImageId",deck.getCurrent().getImageID());
                newRequest.put("User", ParseUser.getCurrentUser());
                newRequest.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {


                        }
                    }
                });

                Intent intent = new Intent(getContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });



        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
