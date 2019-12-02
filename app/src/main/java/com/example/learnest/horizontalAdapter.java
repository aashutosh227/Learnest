package com.example.learnest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class horizontalAdapter extends RecyclerView.Adapter<horizontalAdapter.horizontalViewHolder> {

    private ArrayList<String> cnames;
    private int[] cUrls;
    private Context c_context;

    public horizontalAdapter(Context context, ArrayList<String> names, int[] Urls){
        cnames = names;
        cUrls = Urls;
        c_context = context;
    }

    @NonNull
    @Override
    public horizontalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup,false );
        return new horizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final horizontalViewHolder horizontalViewHolder, int i) {
        horizontalViewHolder.course_title.setText(cnames.get(i));
        horizontalViewHolder.course_image.setImageResource(cUrls[i]);
        horizontalViewHolder.course_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Course Selected: "+horizontalViewHolder.course_title.getText().toString();
                Toast.makeText(c_context,s,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(c_context,VideoActivity.class);
                c_context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cnames.size();
    }

    public static class horizontalViewHolder extends RecyclerView.ViewHolder{

        ImageView course_image;
        TextView course_title;
        public horizontalViewHolder(View itemView){

            super(itemView);
            course_image = itemView.findViewById(R.id.cv_image);
            course_title = itemView.findViewById(R.id.cv_name);


        }

    }
}
