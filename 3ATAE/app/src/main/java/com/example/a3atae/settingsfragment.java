package com.example.a3atae;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link settingsfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class settingsfragment extends Fragment {
    LinearLayout account,wishes,logout,conversations,help,record;
    CircularImageView spp;
    TextView nameofuser;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public settingsfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment settingsfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static settingsfragment newInstance(String param1, String param2) {
        settingsfragment fragment = new settingsfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_settingsfragment, container, false);
        account=layout.findViewById(R.id.account);
        wishes=layout.findViewById(R.id.wishes);
        conversations=layout.findViewById(R.id.conversations);
        record=layout.findViewById(R.id.Record);
        spp=layout.findViewById(R.id.convwithpic);
        Picasso.get().load("http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/profilepics/"+GlobalVariables.profilepic).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(spp);
        nameofuser=layout.findViewById(R.id.convwithusername);
        nameofuser.setText(GlobalVariables.username);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountsettings=new Intent(getContext(),Profile.class);
                startActivity(accountsettings);
            }
        });
        wishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wishesintent=new Intent(getContext(),itemsrecycler.class);
                startActivity(wishesintent);
            }
        });


        conversations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wishesintent=new Intent(getContext(),ConversationsRecycler.class);
                startActivity(wishesintent);
            }
        });



        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentrecord=new Intent(getContext(),record.class);
                startActivity(intentrecord);

            }
        });
        return layout;
    }
}