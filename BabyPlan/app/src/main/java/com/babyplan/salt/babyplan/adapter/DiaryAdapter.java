package com.babyplan.salt.babyplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babyplan.salt.babyplan.R;

import butterknife.ButterKnife;

/**
 * 日记适配器
 *
 * @author : _chf
 * @since : 2017/2/15
 */
public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryViewHolder>{

    private Context context;

    public DiaryAdapter(Context context) {
        this.context = context;
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiaryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_diary,parent,false));
    }

    @Override
    public void onBindViewHolder(DiaryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }


    static class DiaryViewHolder extends RecyclerView.ViewHolder{


        public DiaryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }
}
