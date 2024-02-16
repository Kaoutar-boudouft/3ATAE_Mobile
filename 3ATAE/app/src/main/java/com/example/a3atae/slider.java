package com.example.a3atae;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class slider extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public slider(Context context){
        this.context=context;
    }
    public int[] slideimages={
            R.drawable.bg1,
            R.drawable.bg2,
            R.drawable.bg3,
            R.drawable.bg4

    };
    public String[] slideTitle={
            "Spread the good deed.",
            "Be an example for others",
            "Contribute in society",
            "Be merciful"
    };
    public String[] slideparagraph={
            "Good deeds have the double benefit of helping the intended recipient—and making the doer feel good, as well. ",
            "Being cruel to others can please you for a moment, but it can hurt others for a long time. We must never forget that we also need the charity of others towards us.",
            "For an ideal society is a climate in which random charity is a natural part of everyone's daily life. Not only that but you also experiences a sense of comfort and joy in doing a good job. ",
            "The biggest giving we will give to our society will be our contribution to making other people's lives better."
    };


    @Override
    public int getCount() {
        return slideimages.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==(RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.activity_slider,container,false);
        ImageView im=(ImageView) view.findViewById(R.id.slideimage);
        TextView title=(TextView) view.findViewById(R.id.titleslide) ;
        TextView paragraph=(TextView) view.findViewById(R.id.parag) ;
        im.setImageResource(slideimages[position]);
        title.setText(slideTitle[position]);
        paragraph.setText(slideparagraph[position]);



        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }


}

