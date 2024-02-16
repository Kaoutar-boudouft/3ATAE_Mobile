package com.example.a3atae;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {
FloatingActionButton cpp;
Button btn_confirm;
CircularImageView pp;
Bitmap bitmap;
String encodeImageString;
ProgressDialog progressDialog;
ImageButton eu,ec,uu,uc;
LinearLayout csection,usection,llu,llc;
EditText uet,cet;
ImageView backtomain;
TextView ct,ut;
String uuu="http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/api/UpdateUserName";
String ucu="http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/api/UpdateCity";
String url="http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/api/UpdateProfilePicBase64";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        cpp = findViewById(R.id.changepp);
        pp = findViewById(R.id.qrcode);
        btn_confirm=findViewById(R.id.btn_confirm);
        eu=findViewById(R.id.call);
        usection=findViewById(R.id.usection);
        csection=findViewById(R.id.csection);
        ec=findViewById(R.id.ec);
        uet=findViewById(R.id.uet);
        cet=findViewById(R.id.cet);
        llu=findViewById(R.id.llu);
        llc=findViewById(R.id.llc);
        ct=findViewById(R.id.ct);
        ut=findViewById(R.id.ut);
        ct.setText(GlobalVariables.city);
        ut.setText(GlobalVariables.username);
        uu=findViewById(R.id.uu);
        uc=findViewById(R.id.uc);
        backtomain=findViewById(R.id.backtomain);
        Picasso.get().load("http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/profilepics/"+GlobalVariables.profilepic).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(pp);

        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtomain=new Intent(getApplicationContext(),Home.class);
                startActivity(backtomain);
                finish();
            }
        });

        progressDialog=new ProgressDialog(Profile.this);
        progressDialog.setMessage("Uploading Image Please Wait!");
        progressDialog.setCancelable(false);
        progressDialog.setMax(100);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        eu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usection.setVisibility(View.VISIBLE);
                eu.setVisibility(View.INVISIBLE);
                llu.setVisibility(View.INVISIBLE);
                uet.setText(GlobalVariables.username);
            }
        });

        ec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                csection.setVisibility(View.VISIBLE);
                ec.setVisibility(View.INVISIBLE);
                llc.setVisibility(View.INVISIBLE);
                cet.setText(GlobalVariables.city);

            }
        });

        cpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dexter.withActivity(Profile.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response)
                            {
                                Intent intent=new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Browse Image"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploaddatatodb();


            }
        });

        uu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uet.getText().toString().equals("")){
                    Toast.makeText(Profile.this, "UserName Mustn't be empty!", Toast.LENGTH_SHORT).show();
                    usection.setVisibility(View.INVISIBLE);
                    eu.setVisibility(View.VISIBLE);
                    llu.setVisibility(View.VISIBLE);
                }
                else {
                    UpdateNameToDB(uet.getText().toString());

                }
            }
        });

        uc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cet.getText().toString().equals("")){
                    Toast.makeText(Profile.this, "City Mustn't be empty!", Toast.LENGTH_SHORT).show();
                    csection.setVisibility(View.INVISIBLE);
                    ec.setVisibility(View.VISIBLE);
                    llc.setVisibility(View.VISIBLE);
                }
                else {
                    UpdateCityToDB(cet.getText().toString());

                }
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if(requestCode==1 && resultCode==RESULT_OK)
        {
            Uri filepath=data.getData();
            try
            {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                pp.setImageBitmap(bitmap);
                btn_confirm.setVisibility(View.VISIBLE);
                encodeBitmapImage(bitmap);
               }catch (Exception ex)
            {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void encodeBitmapImage(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] bytesofimage=byteArrayOutputStream.toByteArray();
        encodeImageString=android.util.Base64.encodeToString(bytesofimage, Base64.DEFAULT);
    }


    private void uploaddatatodb()
    {

        progressDialog.show();

        StringRequest request=new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progressDialog.dismiss();
                btn_confirm.setVisibility(View.INVISIBLE);
                GlobalVariables.profilepic=GlobalVariables.username+".jpg";
                Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();
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
                map.put("UserName",GlobalVariables.username);
                map.put("image",encodeImageString);
                return map;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }

    private void UpdateNameToDB(String newusername){
        StringRequest request=new StringRequest(Request.Method.PUT, uuu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progressDialog.dismiss();
                if (response.equals("1")){
                    Toast.makeText(getApplicationContext(),"UserName Updated Successfully!",Toast.LENGTH_LONG).show();
                    GlobalVariables.username=newusername;
                    usection.setVisibility(View.INVISIBLE);
                    eu.setVisibility(View.VISIBLE);
                    llu.setVisibility(View.VISIBLE);
                    ut.setText(GlobalVariables.username);
                }
                if (response.equals("0")){
                    Toast.makeText(getApplicationContext(),"Cannot Update UserName!",Toast.LENGTH_LONG).show();
                }
                else {
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
                map.put("userid",GlobalVariables.userid);
                map.put("UserName",newusername);
                return map;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);



    }

    private void UpdateCityToDB(String newcity) {
        StringRequest request=new StringRequest(Request.Method.PUT, ucu, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                progressDialog.dismiss();
                if (response.equals("1")){
                    Toast.makeText(getApplicationContext(),"City Updated Successfully!",Toast.LENGTH_LONG).show();
                    GlobalVariables.city=newcity;
                    csection.setVisibility(View.INVISIBLE);
                    ec.setVisibility(View.VISIBLE);
                    llc.setVisibility(View.VISIBLE);
                    ct.setText(GlobalVariables.city);
                }
                if (response.equals("0")){
                    Toast.makeText(getApplicationContext(),"Cannot Update City!",Toast.LENGTH_LONG).show();
                }
                else {
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
                map.put("userid",GlobalVariables.userid);
                map.put("city",newcity);
                return map;
            }
        };


        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(request);


    }
}