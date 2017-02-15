package com.babyplan.salt.babyplan.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babyplan.salt.babyplan.R;
import com.babyplan.salt.babyplan.adapter.PrizeAdapter;
import com.yalantis.taurus.PullToRefreshView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StoreFragment extends Fragment {

    @InjectView(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    private PrizeAdapter prizeAdapter;

    public StoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prizeAdapter=new PrizeAdapter(getContext());

        recyclerView.setAdapter(prizeAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshView.setRefreshing(false);
                    }
                },1000);
            }
        });
    }
}
