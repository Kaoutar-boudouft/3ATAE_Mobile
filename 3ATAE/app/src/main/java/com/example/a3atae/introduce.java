package com.example.a3atae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class introduce extends AppCompatActivity {
    ViewPager vp;
    slider sa;
    Button skip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        vp=(ViewPager) findViewById(R.id.vp);
        skip=findViewById(R.id.skip);
        sa=new slider(this);
        vp.setAdapter(sa);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intskip=new Intent(getApplicationContext(),Splash_screen.class);
                startActivity(intskip);
                finish();
            }
        });

    }
}