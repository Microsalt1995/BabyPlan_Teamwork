package com.babyplan.salt.babyplan.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babyplan.salt.babyplan.R;
import com.babyplan.salt.babyplan.adapter.CourseAdapter;
import com.babyplan.salt.babyplan.adapter.ImageAdapter;
import com.github.lzyzsd.circleprogress.DonutProgress;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeFragment extends Fragment {

    @InjectView(R.id.recycler_view_feedback)
    RecyclerView recyclerViewFeedback;
    @InjectView(R.id.recycler_view_lasted)
    RecyclerView recyclerViewLasted;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager feedbackLayoutManager=new LinearLayoutManager(getContext());
        feedbackLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        LinearLayoutManager lastedLayoutManager=new LinearLayoutManager(getContext());
        lastedLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewFeedback.setLayoutManager(feedbackLayoutManager);
        recyclerViewLasted.setLayoutManager(lastedLayoutManager);

        recyclerViewFeedback.setAdapter(new ImageAdapter(getContext(),ImageAdapter.TYPE_FEEDBACK));
        recyclerViewLasted.setAdapter(new ImageAdapter(getContext(),ImageAdapter.TYPE_LASTED));

    }
}
