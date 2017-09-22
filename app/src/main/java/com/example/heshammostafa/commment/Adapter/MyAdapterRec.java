package com.example.heshammostafa.commment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.heshammostafa.commment.CommentAndReplay;
import com.example.heshammostafa.commment.MainActivity;
import com.example.heshammostafa.commment.Model.MyDataModel;
import com.example.heshammostafa.commment.R;
import com.example.heshammostafa.commment.dataSenderByInterface.CommentUpdateModel;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by HeshamMostafa on 8/6/2017.
 */

public class MyAdapterRec extends RecyclerView.Adapter<MyAdapterRec.MYViewHolder> {

    List<MyDataModel> modelList;
    Context context;
    private LayoutInflater mInflater;
    MyDataModel current;

    public MyAdapterRec(Context context, List<MyDataModel> objects) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    public void delete(int position) {
        modelList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MYViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_element, parent, false);
        MYViewHolder holder = new MYViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MYViewHolder holder, final int position) {

        current = modelList.get(position);
        holder.shareNum.setText(String.valueOf(current.getNumberOFShare()));
        holder.CommNum.setText(String.valueOf(current.getNumComment()));
        holder.LikeNum.setText(String.valueOf(current.getNumberOfLike()));
        Picasso.with(context).load(current.getImg()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.imageView);
        holder.Comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CommentAndReplay.class);
                intent.putExtra("position", "" + position);
                context.startActivity(intent);


            }
        });

        holder.Like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.Share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                current = modelList.get(position);
                int Like=current.getNumComment();
                Like=Like+1;
                holder.LikeNum.setText(String.valueOf(Like));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public static class MYViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rootView;
        public ImageView imageView;
        public ImageView Share;
        public ImageView Like;
        public ImageView Comment;
        public TextView shareNum, LikeNum, CommNum;

        public MYViewHolder(View itemView) {
            super(itemView);
            this.rootView = (RelativeLayout) itemView;
            imageView = (ImageView) rootView.findViewById(R.id.img);
            Share = (ImageView) rootView.findViewById(R.id.Share);
            Like = (ImageView) rootView.findViewById(R.id.Like);
            Comment = (ImageView) rootView.findViewById(R.id.Comment);
            shareNum = (TextView) rootView.findViewById(R.id.TShare);
            LikeNum = (TextView) rootView.findViewById(R.id.TLike);
            CommNum = (TextView) rootView.findViewById(R.id.TComment);

        }


    }


}
