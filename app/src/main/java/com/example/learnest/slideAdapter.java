package com.example.learnest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class slideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public slideAdapter(Context context){
        this.context = context;
    }

    //Arrays

    public  int[] slide_images={
            R.drawable.eat,
            R.drawable.sleep,
            R.drawable.code
    };

    public String[] slide_headings={
          "EAT",
          "SLEEP",
          "LEARN"
    };

    public String[] slide_descs={
        "Eat Healthy To Nourish\nYour Body",
            "Sleep Sufficiently\nAt Night To\nCherish Your Days",
            "Learn & Gain\nTo\nFlourish Your Life"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==(RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView imageView=(ImageView) view.findViewById(R.id.icon);
        TextView heading= (TextView) view.findViewById(R.id.heading);
        TextView desc=(TextView) view.findViewById(R.id.description);

        imageView.setImageResource(slide_images[position]);
        heading.setText(slide_headings[position]);
        desc.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container,int position, Object object){
        container.removeView((RelativeLayout)object);
    }
}
