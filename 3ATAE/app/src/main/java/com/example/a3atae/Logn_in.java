package com.example.a3atae;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Logn_in extends AppCompatActivity {
TextView top,bottom,register;
Button login;
EditText username,Password;
RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logn_in);
        top=findViewById(R.id.top);
        bottom=findViewById(R.id.bottom);
        login=findViewById(R.id.login);
        username=findViewById(R.id.username);
        Password=findViewById(R.id.Password);
        register=findViewById(R.id.textView3);
        rq= Volley.newRequestQueue(this);
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(top,"translationX",500,0),
                ObjectAnimator.ofFloat(bottom,"translationX",-500,0)
        );
        animatorSet.setDuration(3000);
        animatorSet.start();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("") || Password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "All fields are required! ", Toast.LENGTH_SHORT).show();
                }
                else {
                    ExecutLoginTask();
                }
            }
        });



    }

    private class Checklogintask extends AsyncTask<String,Integer,String> {


        @Override
        protected String doInBackground(String... strings) {
                        String loginurl="http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/api/LoginApi";
                        StringRequest stringRequest1=new StringRequest(Request.Method.POST, loginurl, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                 if(response.toString().equals("0")){
                                     Toast.makeText(Logn_in.this, "Please Verify Your Email In Our WebSite Before Login From Phone", Toast.LENGTH_SHORT).show();
                                 }
                                 else if (response.toString().equals("1")){
                                     Toast.makeText(Logn_in.this, "Please Verify Your PhoneNumber In Our WebSite Before Login From Phone", Toast.LENGTH_SHORT).show();
                                 }
                                 else if (response.toString().equals("2")){

                                     Toast.makeText(Logn_in.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                     ExecutGetAuthUser("http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/api/GetUserByUserName/"+username.getText().toString());
                                 }
                                 else {
                                     Toast.makeText(Logn_in.this, response.toString(), Toast.LENGTH_SHORT).show();
                                 }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Logn_in.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams(){
                                Map<String,String> params1=new HashMap<String,String>();
                                params1.put("UserName",username.getText().toString());
                                params1.put("password",Password.getText().toString());

                                return params1;
                            }
                        };
                        RequestQueue requestQueue1= Volley.newRequestQueue(getApplicationContext());
                        requestQueue1.add(stringRequest1);


            return "";
        }
    }
    public void ExecutLoginTask(){
        Checklogintask task=new Checklogintask();
        task.execute();
    }

    private class GetAuthUser extends AsyncTask<String,Integer,String> {


        @Override
        protected String doInBackground(String... strings) {
            JsonObjectRequest reqhttp=new JsonObjectRequest(Request.Method.GET, strings[0], null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonObject=response;
                        GlobalVariables.username=jsonObject.getString("UserName");
                        GlobalVariables.profilepic=jsonObject.getString("photo");
                        GlobalVariables.userid=jsonObject.getString("id");
                        GlobalVariables.city=jsonObject.getString("City");
                        GlobalVariables.points=jsonObject.getString("Points");

                        Toast.makeText(Logn_in.this,GlobalVariables.profilepic, Toast.LENGTH_SHORT).show();
                        Intent mainint=new Intent(getApplicationContext(),Home.class);
                        startActivity(mainint);
                        finish();

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Log.d("exception",exception.toString());
                        Toast.makeText(Logn_in.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            rq.add(reqhttp);
            return "done";

        }
    }
    public void ExecutGetAuthUser(String urljson){
        GetAuthUser task=new GetAuthUser();
        task.execute(urljson);
    }


}