package com.morse.ganapp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.morse.ganapp.R;
import com.morse.ganapp.model.ResultEntity;
import com.morse.ganapp.ui.activity.ArtcleActivity;

import java.util.List;

/**
 * 作者：Morse
 * 创建时间：2016/5/19 10:07
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ArtcleAdapter extends RecyclerView.Adapter<ArtcleAdapter.Holder> {

    private Context mContext;
    private List<ResultEntity> mResultEntities;

    public ArtcleAdapter(Context context, List<ResultEntity> entities) {
        mContext = context;
        mResultEntities = entities;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ResultEntity entity = mResultEntities.get(position);
        holder.cardView.setOnClickListener(new MyOnClick(position));
        holder.title.setText(entity.getDesc());
        holder.who.setText(entity.getWho());
        holder.type.setText(entity.getType());
        holder.time.setText(entity.getPublishedAt());
    }

    @Override
    public int getItemCount() {
        return null != mResultEntities ? mResultEntities.size() : 0;
    }

    class MyOnClick implements View.OnClickListener {

        private int position;

        MyOnClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, ArtcleActivity.class);
            intent.putExtra("url", mResultEntities.get(position).getUrl());
            intent.putExtra("title", mResultEntities.get(position).getDesc());
            mContext.startActivity(intent);
        }
    }


    public class Holder extends RecyclerView.ViewHolder {

        private TextView title, who, type, time;
        private CardView cardView;

        public Holder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            title = (TextView) itemView.findViewById(R.id.artcle_title);
            who = (TextView) itemView.findViewById(R.id.artcle_who);
            type = (TextView) itemView.findViewById(R.id.artcle_type);
            time = (TextView) itemView.findViewById(R.id.artcle_public);
        }
    }
}
