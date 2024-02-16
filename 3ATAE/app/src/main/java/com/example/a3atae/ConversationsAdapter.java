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

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ConversationsAdapter extends RecyclerView.Adapter<ConversationsAdapter.LayoutStartour> {
    private ArrayList<Conversation> lisConv;

    public ConversationsAdapter(ArrayList<Conversation> lisConv) {
        this.lisConv = lisConv;
    }

    @NonNull
    @Override
    public ConversationsAdapter.LayoutStartour onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.oneconversation,parent,false);
        return new LayoutStartour(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationsAdapter.LayoutStartour holder, int position) {
        final Conversation t=lisConv.get(position);
        String image=t.getConvwithuser().photo;
        String username=t.getConvwithuser().username;
        String convinfo="Discussion About The Offer: "+t.getConvaboutannonce().getTitle()+" In "+t.getConvaboutannonce().getCity()+" Announced In : "+t.getConvaboutannonce().getAnndate();
        holder.convabout.setText(convinfo);
        Log.d("user",username);
        holder.convwithusername.setText(username);
        Picasso.get().load("http://"+GlobalVariables.ip+"/PfeUsingLaravel8/public/profilepics/"+image).networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.convwithpic);

        holder.call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uri = "tel:0"+t.getConvwithuser().getPhone().trim() ;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                view.getContext().startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return lisConv.size();
    }

    public static class LayoutStartour extends RecyclerView.ViewHolder {

        CircularImageView convwithpic;
        ImageView call;
        TextView convwithusername,convabout;


        public LayoutStartour(@NonNull View itemView) {
            super(itemView);

            convwithpic=itemView.findViewById(R.id.convwithpic);
            convwithusername=itemView.findViewById(R.id.convwithusername);
            convabout=itemView.findViewById(R.id.conabout);
            call=itemView.findViewById(R.id.call);
        }
    }
}
