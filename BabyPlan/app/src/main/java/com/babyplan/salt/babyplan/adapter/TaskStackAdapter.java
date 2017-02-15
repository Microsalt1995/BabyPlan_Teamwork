package com.babyplan.salt.babyplan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.babyplan.salt.babyplan.R;
import com.loopeer.cardstack.CardStackView;
import com.loopeer.cardstack.StackAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 任务卡适配类
 *
 * @author : _chf
 * @since : 2017/2/13
 */
public class TaskStackAdapter extends StackAdapter<String>{

    private static final String colors[]={
            "#827394",
            "#1A4FA3",
            "#F078C0",
            "#055387",
            "#DD6D7D"
    };

    public TaskStackAdapter(Context context) {
        super(context);
    }

    @Override
    public void bindView(String data, int position, CardStackView.ViewHolder holder) {
        TaskItemViewHolder taskItemViewHolder=(TaskItemViewHolder) holder;
        taskItemViewHolder.onBind(position);
    }

    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        return new TaskItemViewHolder(getLayoutInflater().inflate(R.layout.item_task,parent,false));
    }

    static class TaskItemViewHolder extends CardStackView.ViewHolder {

        @InjectView(R.id.CV_task)
        CardView cardView;
        @InjectView(R.id.expand_wrap)
        View expandView;
        @InjectView(R.id.IB_close)
        ImageButton imageButton;

        View itemView;
        public TaskItemViewHolder(View view) {
            super(view);
            itemView=view;
            ButterKnife.inject(this,view);
        }

        @Override
        public void onItemExpand(boolean b) {
            expandView.setVisibility(b ? View.VISIBLE : View.GONE);
            imageButton.setVisibility(b ? View.VISIBLE : View.GONE);
        }

        public void onBind(int position){
            cardView.setCardBackgroundColor(Color.parseColor(colors[position%5]) );
        }

    }
}
