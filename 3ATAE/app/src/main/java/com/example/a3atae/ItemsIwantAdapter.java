package com.example.a3atae;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ItemsIwantAdapter extends RecyclerView.Adapter<ItemsIwantAdapter.LayoutStartour> {
    private ArrayList<Item> listt;

    public ItemsIwantAdapter(ArrayList<Item> listt) {
        this.listt = listt;
    }

    @NonNull
    @Override
    public ItemsIwantAdapter.LayoutStartour onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.carddesign,parent,false);
        return new LayoutStartour(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsIwantAdapter.LayoutStartour holder, int position) {
        final Item t=listt.get(position);
        String images=t.getImage();
        StringTokenizer eachimage = new StringTokenizer(images, "**");
        String first = eachimage.nextToken();
        Log.d("firstimage",first);
        Picasso.get().load("http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/sdannoncespics/"+first).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).resize(400,250).into(holder.img);
        holder.count.setText(t.getTitle());
        holder.card.setText(t.getCity());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intdetails=new Intent(v.getContext(),itemdetails.class);
                intdetails.putExtra("id",t.getIdItem());
                intdetails.putExtra("image",first);
                intdetails.putExtra("title",t.getTitle());
                intdetails.putExtra("description",t.getDescription());
                intdetails.putExtra("city",t.getCity());
                intdetails.putExtra("anndate",t.getAnnDate());
                v.getContext().startActivity(intdetails);
            }
        });
       /* holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentshare=new Intent(Intent.ACTION_SEND);
                intentshare.setType("text/plain");
                intentshare.putExtra(Intent.EXTRA_TEXT,t.getTitle()+"\n"+"Do You Like This Item?"+"\n"+"Visite Our Website :"+"\n"+"http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public"+"\n"+"Or Install Our Mobile Version!");
                v.getContext().startActivity(intentshare);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return listt.size();
    }

    public static class LayoutStartour extends RecyclerView.ViewHolder {

        ImageView img;
        TextView card,count;

        public LayoutStartour(@NonNull View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.img);
            card=(TextView) itemView.findViewById(R.id.card);
            count=(TextView) itemView.findViewById(R.id.count);


        }
    }
}
