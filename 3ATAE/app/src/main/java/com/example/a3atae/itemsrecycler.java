package com.example.a3atae;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class itemsrecycler extends AppCompatActivity {
    RecyclerView dataList;

    Adapter adapter;
    ArrayList<Item> IL;
    ImageView Backtomain;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemsrecycler);
        dataList = findViewById(R.id.dataList);

        IL=new ArrayList<Item>();

        Backtomain=findViewById(R.id.backtomain);

        Backtomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtomain=new Intent(getApplicationContext(),Home.class);
                startActivity(backtomain);
                finish();
            }
        });

        final String[] myresponse = new String[1];
        OkHttpClient client = new OkHttpClient();
        String url="http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/api/getitemsofuser/"+GlobalVariables.userid;
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
                            JSONObject Item = jsonArray.getJSONObject(i);
                            Log.d("itmeiwant", String.valueOf(Item));
                            String idi=Item.getString("id");
                            String t = Item.getString("Title");
                            String D = Item.getString("Description");
                            String AD = Item.getString("AnnDate");
                            String C = Item.getString("City");
                            String img = Item.getString("images");

                            Item s = new Item(idi,t,D,AD,C,img);
                            IL.add(s);

                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new Adapter(IL);
                                if(IL.size()==0){
                                    Intent emptyrecyclerintent=new Intent(getApplicationContext(),NoItemsFounded.class);
                                    startActivity(emptyrecyclerintent);
                                    finish();
                                }
                                GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2,GridLayoutManager.VERTICAL,false);
                                dataList.setLayoutManager(gridLayoutManager);
                                dataList.setAdapter(adapter);


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
