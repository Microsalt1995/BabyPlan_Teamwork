package com.babyplan.salt.babyplan;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.babyplan.salt.babyplan.adapter.DiaryAdapter;
import com.yalantis.taurus.PullToRefreshView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DiaryActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;
    @InjectView(R.id.recycler_view)
    RecyclerView recyclerView;

    private DiaryAdapter diaryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        ButterKnife.inject(this);

        setSupportActionBar(toolbar);
        final ActionBar ab=getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

        diaryAdapter=new DiaryAdapter(this);
        recyclerView.setAdapter(diaryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
