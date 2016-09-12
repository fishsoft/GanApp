package com.morse.ganapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.morse.ganapp.R;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.ui.activity.DailyActivity;

import java.util.List;

/**
 * 作者：Morse
 * 创建时间：2016/6/14 17:13
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.Holder> {

    private Context mContext;
    private List<ResultEntity> mResultEntities;

    public WelfareAdapter(Context context, List<ResultEntity> entities) {
        mContext = context;
        mResultEntities = entities;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.welfare_item, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(mContext)
                .load(mResultEntities.get(position).getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.ic_launcher)
                .into(holder.mIvWelfareImg);
        holder.mTvWelfareDesc.setText(mResultEntities.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        if (mResultEntities.size() > 0) {
            return mResultEntities.size();
        } else {
            return 0;
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        public ImageView mIvWelfareImg;
        public TextView mTvWelfareDesc;

        public Holder(View itemView) {
            super(itemView);
            mIvWelfareImg = (ImageView) itemView.findViewById(R.id.iv_welfare_img);
            mTvWelfareDesc = (TextView) itemView.findViewById(R.id.tv_welfare_desc);
            itemView.setOnClickListener(new WelfareClick(getPosition()+1));
        }
    }

    class WelfareClick implements View.OnClickListener {

        private int position;

        public WelfareClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.welfare_item:
                    String time = mResultEntities.get(position).getCreatedAt();
                    String s = (String) time.subSequence(0, 10);
                    String[] strings = s.split("-");
                    Intent intent = new Intent(mContext, DailyActivity.class);
//                    StringBuffer buffer=new StringBuffer("http://gank.io/api/history/content/day");
//                    for (String url : strings) {
//                        buffer.append("/"+url);
//                    }
//                    intent.putExtra("url", "http://gank.io/");
                    intent.putExtra("date", strings);
                    mContext.startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

}
