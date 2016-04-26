package com.demotest.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class MarshonRecyclerViewActivity extends BaseActivity {

    private CommonAdapter adapter;
    private ChooseAnimatorsAdapter animAdapter;
    private List datas;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marsreview);
        spinner.setVisibility(View.GONE);
        toolBar.setTitle("Marshon");

        //make full data test
        datas = new ArrayList();
        for(int i=0;i<1000;i++){
            datas.add("");
        }


        //1.do somethings about adapter;
        adapter = new CommonAdapter<String>(this, R.layout.listitem, datas) {
            @Override
            public void convert(final ViewHolder holder, String s) {
                //1.1do somethings;
                final SwipeLayout swipeLayout= (SwipeLayout) holder.getConvertView().findViewById(R.id.swipelayout);
                TextView tv_delete= (TextView) swipeLayout.findViewById(R.id.tv_delete);

                swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
                swipeLayout.addDrag(SwipeLayout.DragEdge.Right, tv_delete);

                tv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipeLayout.close(true);
                        adapter.notifyItemRemoved(holder.getLayoutPosition());
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



    }



    @Override
    public List<String> getSpinnerData() {
        return null;
    }

    @Override
    public void onSpinnerItemClick(int position) {

    }
}
