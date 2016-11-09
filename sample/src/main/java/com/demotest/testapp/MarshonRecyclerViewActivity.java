package com.demotest.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.demotest.testapp.adapter.MarshonRecyclerAdapter;
import com.marshon.mrecyclerview.MRecyclerView;
import com.marshon.swipe.SwipeWraper;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Administrator on 2016/4/25.
 */
public class MarshonRecyclerViewActivity extends AppCompatActivity {
    private ChooseAnimatorsAdapter animAdapter;
    private List datas;
    private MRecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marsreview);
        mRecyclerView = (MRecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //1.make full data test
        datas = makeDatas();
        MarshonRecyclerAdapter adapter= new MarshonRecyclerAdapter(this,R.layout.listitem, datas) {
            @Override
            public void convert(final RecyclerView.ViewHolder holder, final int position) {
                View deleteView = holder.itemView.findViewById(R.id.tv_delete);
                final SwipeWraper swipelayout = (SwipeWraper) holder.itemView.findViewById(R.id.swipelayout);
                deleteView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipelayout.close(true);
                        datas.remove(holder.getLayoutPosition()-1);
                        animAdapter.notifyItemRemoved(holder.getLayoutPosition());
                    }
                });
            }
        };

        //2.set up recyclerview
        final LinearLayoutManager manager=new LinearLayoutManager(this);
        //3.给adapter装饰上animAdapter
        animAdapter =new ChooseAnimatorsAdapter(adapter);
        animAdapter.setAnimatorFlag(ChooseAnimatorsAdapter.ANIMATORFLAG_SCALE);
        mRecyclerView.setAdapter(animAdapter);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setLoadingListener(new MRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<15;i++){
                            datas.add("");
                        }
                        animAdapter.notifyDataSetChanged();
                    }
                },3000);
            }
        });
    }


    protected List<String> makeDatas() {
        List datas = new ArrayList();
        for(int i=0;i<400;i++){
            datas.add(""+i);
        }
        return datas;
    }

}
