package com.example.a3atae;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NestedAdapter extends RecyclerView.Adapter<NestedAdapter.NestedViewHolder> {

    private List<SubCategory> mList;

    public NestedAdapter(List<SubCategory> mList){
        this.mList = mList;
    }
    @NonNull
    @Override
    public NestedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eachsubcategory , parent , false);
        return new NestedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NestedViewHolder holder, int position) {
        final SubCategory t=mList.get(position);

        holder.mTv.setText(t.getNomsubcat());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),t.getIdsubcat(), Toast.LENGTH_SHORT).show();
                Intent fetch=new Intent(view.getContext(),Fetchoffersbysubcat.class);
                fetch.putExtra("catid",t.getIdsubcat());
                fetch.putExtra("catname",t.getNomsubcat());
                view.getContext().startActivity(fetch);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class NestedViewHolder extends RecyclerView.ViewHolder{
        private TextView mTv;
        CardView card;
        public NestedViewHolder(@NonNull View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.nestedItemTv);
            card=itemView.findViewById(R.id.cardView);
        }
    }
}
