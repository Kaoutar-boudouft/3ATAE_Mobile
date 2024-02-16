package com.example.a3atae;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
   public RecyclerView categories;
    public List<DataModel> mList;
    // List<SubCategory> subcats;
    public ItemAdapter adapter;
    public RequestQueue rq;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View layout= inflater.inflate(R.layout.fragment_home, container, false);

        categories=layout.findViewById(R.id.categoryrecycler);
        categories.bringToFront();
     //  categories.setLayoutManager(new LinearLayoutManager(getContext()));
     //   categories.setHasFixedSize(true);

        mList = new ArrayList<DataModel>();
        //  subcats=new ArrayList<SubCategory>();

        rq= Volley.newRequestQueue(getContext());

        final String[] myresponse = new String[1];
        OkHttpClient client = new OkHttpClient();
        String url="http://192.168.137.10/PfeUsingLaravel8/public/api/GettingSDCategoryApi/all";
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

                            JSONObject category = jsonArray.getJSONObject(i);
                            Log.d("itmeiwant", String.valueOf(category));
                            String id=category.getString("id");
                            String NomCategory = category.getString("NomCategory");
                            String ImageCategory = category.getString("ImageCategory");

                            Log.d("test",NomCategory);

                            List<SubCategory> subcats= Execut("http://192.168.137.10/PfeUsingLaravel8/public/api/GettingSDSousCategoryApi/"+id);
                            mList.add(new DataModel(subcats , NomCategory,ImageCategory));

                            //  subcats.clear();


                          /*  Item s = new Item(idi,t,D,AD,C,img);
                            IL.add(s);*/

                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new ItemAdapter(mList);
                                categories.setLayoutManager(new LinearLayoutManager(getContext()));
                                categories.setAdapter(adapter);


                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("rlti", String.valueOf(e));
                    }
                }
            }
        });



        // Inflate the layout for this fragment
        return layout;
    }
    private class GetSubCatsByCat extends AsyncTask<String,Integer,String> {
        List<SubCategory> subcats;

        public GetSubCatsByCat(List<SubCategory> subcats) {
            this.subcats = subcats;
        }

        @Override
        protected String doInBackground(String... strings) {
            JsonArrayRequest reqhttp=new JsonArrayRequest(Request.Method.GET, strings[0], null, new com.android.volley.Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        JSONArray jsonArray=response;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject category = jsonArray.getJSONObject(i);
                            //Log.d("itmeiwant", String.valueOf(category));
                            String idsubcat=category.getString("id");
                            String NomSousCategory = category.getString("NomSousCategory");
                            SubCategory subCategory=new SubCategory(idsubcat,NomSousCategory);

                            subcats.add(subCategory);

                          /*  Item s = new Item(idi,t,D,AD,C,img);
                            IL.add(s);*/

                        }
                        System.out.println("ArrayList before using the ArrayList.clear() method: " + subcats);


                        /*for (int j=0;j<subcats.size();j++){
                            Log.d("Subcategory", String.valueOf(subcats.get(j).nomsubcat));
                        }*/


                     /*   GlobalVariables.username=jsonObject.getString("UserName");
                        GlobalVariables.profilepic=jsonObject.getString("photo");
                        GlobalVariables.userid=jsonObject.getString("id");
                        GlobalVariables.city=jsonObject.getString("City");
                        GlobalVariables.points=jsonObject.getString("Points");

                        Toast.makeText(Logn_in.this,GlobalVariables.profilepic, Toast.LENGTH_SHORT).show();
                        Intent mainint=new Intent(getApplicationContext(),Home.class);
                        startActivity(mainint);
                        finish();*/

                    } catch (Exception exception) {
                        exception.printStackTrace();
                        Log.d("exception",exception.toString());
                        // Toast.makeText(Logn_in.this, "error", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            rq.add(reqhttp);
            return "done";

        }
    }
    public List<SubCategory> Execut(String urljson){
        GetSubCatsByCat task=new GetSubCatsByCat(new ArrayList<SubCategory>());
        task.execute(urljson);
        return task.subcats;

    }
}