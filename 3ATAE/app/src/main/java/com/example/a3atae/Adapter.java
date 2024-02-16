package com.example.a3atae;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    ArrayList<Item> items;

    public Adapter(ArrayList<Item> items){
        this.items = items;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item t=items.get(position);
        holder.title.setText(t.getTitle());
        holder.city.setText(t.getCity());
        String images=t.getImage();
        StringTokenizer eachimage = new StringTokenizer(images, "**");
        String first = eachimage.nextToken();
        Picasso.get().load("http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/sdannoncespics/"+first).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).resize(400,250).into(holder.gridIcon);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intdetails=new Intent(view.getContext(),itemdetails.class);
                intdetails.putExtra("id",t.getIdItem());
                intdetails.putExtra("image",first);
                intdetails.putExtra("title",t.getTitle());
                intdetails.putExtra("description",t.getDescription());
                intdetails.putExtra("city",t.getCity());
                intdetails.putExtra("anndate",t.getAnnDate());
                view.getContext().startActivity(intdetails);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,city;
        ImageView gridIcon;
        CardView card;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            gridIcon = itemView.findViewById(R.id.imageView2);
            card=itemView.findViewById(R.id.card);
            city=itemView.findViewById(R.id.textView3);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                }
            });*/
        }
    }
}
