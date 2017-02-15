package com.babyplan.salt.babyplan.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babyplan.salt.babyplan.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 奖品适配器
 *
 * @author : _chf
 * @since : 2017/2/14
 */
public class PrizeAdapter  extends RecyclerView.Adapter<PrizeAdapter.PrizeViewHolder> {

    private Context context;

    public PrizeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PrizeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PrizeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_prize,parent,false));
    }

    @Override
    public void onBindViewHolder(PrizeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 24;
    }

    static class PrizeViewHolder extends RecyclerView.ViewHolder{

        @InjectView(R.id.rootView)
        View rootView;

        public PrizeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }

}
