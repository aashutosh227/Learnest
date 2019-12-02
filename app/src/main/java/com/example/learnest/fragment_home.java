package com.example.learnest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;


public class fragment_home extends Fragment {

    private RecyclerView recyclerView;
    private horizontalAdapter adapter;
    private ArrayList<String> mnames = new ArrayList<String>();
    private int[] mImageUrls = {R.drawable.python,R.drawable.c_plus,R.drawable.java,R.drawable.c};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        mnames.add("Python");
        mnames.add("C++");
        mnames.add("JAVA");
        mnames.add("C");

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linearLayoutManager.setReverseLayout(false);
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new horizontalAdapter(getActivity(),mnames,mImageUrls);
        recyclerView.setAdapter(adapter);

        return view;
    }



}
