package com.babyplan.salt.babyplan.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.babyplan.salt.babyplan.DiaryActivity;
import com.babyplan.salt.babyplan.R;
import com.babyplan.salt.babyplan.StoreActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ProfileFragment extends Fragment {

    @InjectView(R.id.btn_diary)
    Button btnDiary;
<<<<<<< HEAD
    @InjectView(R.id.btn_prize)
    Button btnPrize;
=======
    @InjectView(R.id.tv_account)
    TextView tv_Account;
>>>>>>> 892ec7aa3638e33de8647187ff64033fffee6294

    public ProfileFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.inject(this,view);
        tv_Account.setText("111");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnPrize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), StoreActivity.class);
                startActivity(intent);
            }
        });

        btnDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), DiaryActivity.class);
                startActivity(intent);
            }
        });

    }

}
