package com.demotest.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.demotest.testapp.R;

import java.util.List;

/**
 * Created by Marshon.Chen on 2016/7/26.
 * DESC:
 */
public abstract class MarshonRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mDatas;
    private  Context mContext;

    public MarshonRecyclerAdapter(Context context,List<String> mDatas){
        this.mContext=context;
        this.mDatas=mDatas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView=View.inflate(mContext, R.layout.listitem,null);
        return new MViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        convert(holder,position);
    }

    public abstract void convert(RecyclerView.ViewHolder holder, final int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class  MViewHolder extends RecyclerView.ViewHolder{

        public MViewHolder(View itemView) {
            super(itemView);
        }
    }
}
