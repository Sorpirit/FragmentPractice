package com.practice.fragmentpractice;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class FilmsList extends Fragment {


    //Context context;
    RecyclerView recyclerView;
    ArrayList<FilmStats> items;
    FilmStatsRecyclerAdapter adapter;


    public FilmsList() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ///View view = inflater.inflate(R.layout.fragment_films_list, container, false);
        View drawer = inflater.inflate(R.layout.fragment_films_list, container, false);
        recyclerView = (RecyclerView) drawer.findViewById(R.id.relist);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        items = new ArrayList<>();
        adapter = new FilmStatsRecyclerAdapter(items);
        recyclerView.setAdapter(adapter);
        //recyclerView = view.findViewById(R.id.relist);
        loadData();

        return drawer;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();


    }

    private void loadData(){
        final DataLoader loader = new DataLoader(items);
        loader.setLoaderListener(new LoaderListener() {
            @Override
            public void onProgress() {
                //adapter.notifyItemInserted(adapter.getItemCount()-1);
            }

            @Override
            public void onResult() {
                adapter.notifyDataSetChanged();
            }
        });

        loader.execute();
    }

}
