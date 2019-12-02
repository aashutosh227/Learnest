package com.example.learnest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class rv2_adapter extends RecyclerView.Adapter<rv2_adapter.rv2_viewHolder> implements Filterable {

    private ArrayList<String> cnames;
    private List<String> cnames2;
    private int[] c_urls;
    private Context c_context;

    public rv2_adapter(ArrayList<String> names,int[] urls, Context context ){

        cnames = names;
        c_urls = urls;
        c_context = context;
        cnames2 = new ArrayList<String>(cnames);

    }

    @NonNull
    @Override
    public rv2_viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_layout2,viewGroup,false);
        return new rv2_viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final rv2_viewHolder rv2_viewHolder, int i) {
        rv2_viewHolder.textView.setText(cnames.get(i));
        rv2_viewHolder.imageView.setImageResource(c_urls[i]);
        rv2_viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Course Selected: "+rv2_viewHolder.textView.getText().toString();
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

    @Override
    public Filter getFilter() {
        return filter1;
    }

    private Filter filter1 = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length()==0){
                filteredList.addAll(cnames2);
            }
            else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(String item : cnames2){
                    if(item.toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            cnames.clear();
            cnames.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class rv2_viewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        RelativeLayout relativeLayout;

        public rv2_viewHolder(View itemview){
            super(itemview);

            relativeLayout = itemview.findViewById(R.id.favs_rv);
            imageView = itemview.findViewById(R.id.rv2_img);
            textView =  itemview.findViewById(R.id.rv2_title);
        }
    }
}
