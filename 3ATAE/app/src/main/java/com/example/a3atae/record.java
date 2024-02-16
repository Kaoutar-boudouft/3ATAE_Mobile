package com.example.a3atae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class record extends AppCompatActivity {

    ImageView qrcode,backtomain;
    TextView points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        qrcode=findViewById(R.id.qrcode);
        points=findViewById(R.id.points);
        backtomain=findViewById(R.id.backtomain);
        GlideToVectorYou
                .init()
                .with(getApplicationContext())
                .load(Uri.parse("http://192.168.16.215/PfeUsingLaravel8/public/usersqr/"+GlobalVariables.userid+".svg"), qrcode);
        points.setText(GlobalVariables.points);

        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtomain=new Intent(getApplicationContext(),Home.class);
                startActivity(backtomain);
                finish();
            }
        });
    }
}