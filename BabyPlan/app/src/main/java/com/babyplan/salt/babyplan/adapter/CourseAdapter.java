package com.babyplan.salt.babyplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.babyplan.salt.babyplan.R;

import butterknife.ButterKnife;

/**
 * 课程适配器
 *
 * @author : _chf
 * @since : 2017/2/21
 */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {


    private Context context;

    public CourseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CourseViewHolder(LayoutInflater.from(context).inflate(R.layout.item_course,parent,false));
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder{



        public CourseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }

}
