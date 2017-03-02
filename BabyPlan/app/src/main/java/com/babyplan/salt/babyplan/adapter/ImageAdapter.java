package com.babyplan.salt.babyplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.babyplan.salt.babyplan.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 图片适配器
 *
 * @author : _chf
 * @since : 2017/3/2
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context context;
    private int type;

    public static final int TYPE_FEEDBACK=0x145;
    public static final int TYPE_LASTED=0x187;

    public ImageAdapter(Context context) {
        this.context = context;
    }

    public ImageAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image,parent,false));
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        switch (type){
            case TYPE_FEEDBACK:
                holder.content.setImageResource(R.drawable.sample_feedback);
                break;
            case TYPE_LASTED:
                holder.content.setImageResource(R.drawable.sample_diary);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 15;
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.image_view)
        ImageView content;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this,itemView);
        }
    }

}
