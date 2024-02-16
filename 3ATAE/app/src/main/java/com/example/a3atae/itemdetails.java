package com.example.a3atae;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class itemdetails extends AppCompatActivity {
ImageView image,backtomain;
TextView title,city,anndate,description;
Button cancel;
ProgressDialog progressDialog;
String ciiu="http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/api/cancelItemIwant";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemdetails);
        image=findViewById(R.id.dialog_box_item_image);
        title=findViewById(R.id.dialog_box_item_title);
        city=findViewById(R.id.dialog_box_item_city);
        anndate=findViewById(R.id.dialog_box_item_date);
        description=findViewById(R.id.dialog_box_item_description);
        cancel=findViewById(R.id.Cancel);
        backtomain=findViewById(R.id.backtomain);

        progressDialog=new ProgressDialog(itemdetails.this);
        progressDialog.setMessage("Canceling Image Please Wait!");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtomain=new Intent(getApplicationContext(),itemsrecycler.class);
                startActivity(backtomain);
                finish();
            }
        });

        String idfromintent=getIntent().getExtras().getString("id");
        String imagefromintent=getIntent().getExtras().getString("image");
        String titlefromintent=getIntent().getExtras().getString("title");
        String cityfromintent=getIntent().getExtras().getString("city");
        String anndatefromintent=getIntent().getExtras().getString("anndate");
        String descriptionfromintent=getIntent().getExtras().getString("description");


        Picasso.get().load("http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/sdannoncespics/"+imagefromintent).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).resize(600,600).into(image);
        title.setText(titlefromintent);
        city.setText(cityfromintent);
        anndate.setText(anndatefromintent);
        description.setText(descriptionfromintent);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoveItemIwant(idfromintent);
            }
        });

    }
    private void RemoveItemIwant(String annid) {
        progressDialog.show();
        StringRequest request=new StringRequest(Request.Method.POST,ciiu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progressDialog.dismiss();
                if (response.equals("")){

                }
                if (response.equals("0")){
                    Toast.makeText(getApplicationContext(),"Cannot Canceled Item Please try later!",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Canceled Successfully!",Toast.LENGTH_LONG).show();
                    Intent backtomain=new Intent(getApplicationContext(),itemsrecycler.class);
                    startActivity(backtomain);
                    finish();
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> map=new HashMap<String, String>();
                map.put("username",GlobalVariables.userid);
                map.put("annid",annid);
                return map;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }
}