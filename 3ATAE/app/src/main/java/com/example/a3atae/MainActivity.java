package com.example.a3atae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
LinearLayout account,wishes,logout,conversations,help,record;
CircularImageView spp;
TextView nameofuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        account=findViewById(R.id.account);
        wishes=findViewById(R.id.wishes);
        logout=findViewById(R.id.logout);
        conversations=findViewById(R.id.conversations);
        help=findViewById(R.id.help);
        record=findViewById(R.id.Record);
        spp=findViewById(R.id.convwithpic);
        Picasso.get().load("http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/profilepics/"+GlobalVariables.profilepic).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(spp);
        nameofuser=findViewById(R.id.convwithusername);
        nameofuser.setText(GlobalVariables.username);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent accountsettings=new Intent(getApplicationContext(),Profile.class);
                startActivity(accountsettings);
                finish();
            }
        });
        wishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wishesintent=new Intent(getApplicationContext(),itemsrecycler.class);
                startActivity(wishesintent);
                finish();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutintent=new Intent(getApplicationContext(),Logn_in.class);
                startActivity(logoutintent);
                GlobalVariables.userid="";
                GlobalVariables.city="";
                GlobalVariables.username="";
                GlobalVariables.profilepic="";
                finish();
            }
        });

        conversations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wishesintent=new Intent(getApplicationContext(),ConversationsRecycler.class);
                startActivity(wishesintent);
                finish();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intentshare=new Intent(Intent.ACTION_SENDTO);
                    intentshare.setData(Uri.parse("mailto:"));
                    intentshare.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { "ctrlz.kaoutar.nouhaila@gmail.com" });
                    intentshare.putExtra(Intent.EXTRA_SUBJECT,"Asking For Support About AATAE App");
                    startActivity(intentshare);

            }
        });

        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentrecord=new Intent(getApplicationContext(),record.class);
                startActivity(intentrecord);
                finish();

            }
        });
    }
}