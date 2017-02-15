package com.babyplan.salt.babyplan.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babyplan.salt.babyplan.R;
import com.babyplan.salt.babyplan.adapter.TaskStackAdapter;
import com.loopeer.cardstack.CardStackView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TasksFragment extends Fragment implements CardStackView.ItemExpendListener{

    @InjectView(R.id.CAV)
    CardStackView cardStackView;

    private TaskStackAdapter taskStackAdapter;
    private ArrayList<String> strings;

    public TasksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
        ButterKnife.inject(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        taskStackAdapter=new TaskStackAdapter(getContext());
        cardStackView.setAdapter(taskStackAdapter);
        cardStackView.setItemExpendListener(this);
        strings=new ArrayList<>();
        for (int i=0;i<12;i++)
            strings.add(i+"");
        taskStackAdapter.updateData(strings);

    }

    @Override
    public void onItemExpend(boolean expend) {

    }
}
