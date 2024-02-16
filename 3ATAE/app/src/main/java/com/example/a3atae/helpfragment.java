package com.example.a3atae;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.navigation.NavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link helpfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class helpfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public helpfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment helpfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static helpfragment newInstance(String param1, String param2) {
        helpfragment fragment = new helpfragment();
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
        View layout= inflater.inflate(R.layout.fragment_helpfragment, container, false);

        Intent intentshare=new Intent(Intent.ACTION_SENDTO);
        Intent int1=new Intent(getContext(),Home.class);
        intentshare.setData(Uri.parse("mailto:"));
        intentshare.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "ctrlz.kaoutar.nouhaila@gmail.com" });
        intentshare.putExtra(Intent.EXTRA_SUBJECT,"Asking For Support About AATAE App");
        startActivity(int1);
        startActivity(intentshare);


        return layout;

    }
}