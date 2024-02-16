package com.example.a3atae;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class ConversationsRecycler extends AppCompatActivity {
    ConversationsAdapter CA;
    ArrayList<Conversation> CL;
    RecyclerView CR;
    ImageView backtomain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations_recycler);

        CR=findViewById(R.id.covrecycler);
        CL=new ArrayList<Conversation>();
        backtomain=findViewById(R.id.backtomain);

        backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtomain=new Intent(getApplicationContext(),Home.class);
                startActivity(backtomain);
                finish();
            }
        });

        final String[] myresponse = new String[1];
        OkHttpClient client = new OkHttpClient();
        String url="http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/api/getConversationOfUser/"+GlobalVariables.userid;
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    myresponse[0] = response.body().string();
                    try {
                        JSONArray jsonArray = new JSONArray(myresponse[0]);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject Conv = jsonArray.getJSONObject(i);
                            Log.d("Conv", String.valueOf(Conv));
                            String conversationid=Conv.getString("conversationid");
                            String annonceid=Conv.getString("annonceid");
                            String userid=Conv.getString("userid");
                            String UserName=Conv.getString("UserName");
                            String usercity=Conv.getString("usercity");
                            String photo=Conv.getString("photo");
                            String phone=Conv.getString("phone");
                            String Title=Conv.getString("Title");
                            String Description=Conv.getString("Description");
                            String AnnDate=Conv.getString("AnnDate");
                            String City=Conv.getString("City");
                            String images=Conv.getString("images");
                            String AnnonceurId=Conv.getString("AnnonceurId");

                            User convwith=new User(userid,UserName,usercity,photo,phone);
                            Annonce convabout=new Annonce(annonceid,Title,Description,images,AnnDate,AnnonceurId,City);
                            Conversation conv=new Conversation(conversationid,convwith,convabout);

                            CL.add(conv);

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(CL.size()==0){
                                    Intent emptyrecyclerintent=new Intent(getApplicationContext(),nonetwork.class);
                                    startActivity(emptyrecyclerintent);
                                    finish();
                                    Toast.makeText(ConversationsRecycler.this, "walo", Toast.LENGTH_SHORT).show();
                                }

                                else{
                                    for (int i=0;i<CL.size();i++){
                                        Log.d("element",CL.get(i).getConvwithuser().username.toString());
                                    }
                                    CA = new ConversationsAdapter(CL);
                                    Log.d("itemscount", String.valueOf(CA.getItemCount()));
                                    CR.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    CR.setAdapter(CA);

                                }


                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}