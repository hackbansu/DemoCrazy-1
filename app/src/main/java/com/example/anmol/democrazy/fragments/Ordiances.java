package com.example.anmol.democrazy.fragments;

/**
 * Created by anmol on 27/8/17.
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anmol.democrazy.adapters.BillsLaidAdapter;
import com.example.anmol.democrazy.R;

@SuppressLint("ValidFragment")
public class Ordiances extends Fragment {

    Context ctx;

    @SuppressLint("ValidFragment")
    public Ordiances(Context ctx) {

        this.ctx=ctx;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.bills_laid_fragment, container, false);


//        RecyclerView rv= (RecyclerView) view.findViewById(R.id.BillsLaidRv);
//        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ctx);
//        RecyclerView.Adapter adapter=new BillsLaidAdapter(ctx);
//        rv.setLayoutManager(layoutManager);
//        rv.setAdapter(adapter);
        return view;

    }

}