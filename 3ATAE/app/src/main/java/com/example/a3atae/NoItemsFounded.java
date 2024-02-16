package com.example.a3atae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NoItemsFounded extends AppCompatActivity {
ImageView Backtomain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_items_founded);
        Backtomain=findViewById(R.id.backtomain);
        Backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtomain=new Intent(getApplicationContext(),Home.class);
                startActivity(backtomain);
                finish();
            }
        });
    }
}