package com.babyplan.salt.babyplan.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babyplan.salt.babyplan.R;
import com.babyplan.salt.babyplan.adapter.CourseAdapter;
import com.yalantis.phoenix.PullToRefreshView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ExploreFragment extends Fragment {

    @InjectView(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CourseAdapter courseAdapter;

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        courseAdapter=new CourseAdapter(getContext());

        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
