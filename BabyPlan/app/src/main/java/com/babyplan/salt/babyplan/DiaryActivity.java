package com.babyplan.salt.babyplan;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.babyplan.salt.babyplan.adapter.DiaryAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DiaryActivity extends AppCompatActivity {

    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    private DiaryAdapter diaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        setTitle("我的成长日记");

        final ActionBar ab=getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

        ButterKnife.inject(this);

        diaryAdapter=new DiaryAdapter(this);
        recyclerView.setAdapter(diaryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }


}
