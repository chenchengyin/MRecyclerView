package com.demotest.testapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.base.adapter.recyclerview.MultiItemCommonAdapter;
import com.zhy.base.adapter.recyclerview.MultiItemTypeSupport;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public class MarshonFastAdapterActivity extends BaseActivity {

    private XRecyclerView mRecyclerView;
    private CommonAdapter<String> simpleAdapter;
    private MultiItemCommonAdapter<String> multiItemAdapter;
    private MultiItemTypeSupport<String> muliTypeSupport;
    private XRecyclerView mRecyclerView2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marsreview);

        mRecyclerView=(XRecyclerView)findViewById(R.id.mRecyclerView);
        mRecyclerView2=(XRecyclerView)findViewById(R.id.mRecyclerView2);


        //十行代码就能实现一个adapter ,是不是很激动? 放心,效率是跟的上的
        makeSinpleAdapter();
        //令人兴奋的是,还支持多个item  设置type数量,并且根据type类型返回layout resid
        makeMultiAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(simpleAdapter);
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setAdapter(multiItemAdapter);
        mRecyclerView.setPullRefreshEnabled(false);
        mRecyclerView2.setPullRefreshEnabled(false);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView2.setLoadingMoreEnabled(false);
    }

    private void makeMultiAdapter() {
        muliTypeSupport= new MultiItemTypeSupport<String>() {
            @Override
            public int getLayoutId(int itemType) {

                switch (itemType) {
                    case 0:
                        return R.layout.listitem2;
                    case 1:
                        return R.layout.listitem;
                }
                return 0;
            }

            @Override
            public int getItemViewType(int position, String s) {
                return position%2;
            }
        };
        multiItemAdapter= new MultiItemCommonAdapter<String>(this, MakeLovelyData.makeDatas(), muliTypeSupport) {
            @Override
            public void convert(ViewHolder holder, String s) {

                switch (holder.getLayoutId()){
                    case R.layout.listitem2:
                        holder.setText(R.id.text1, "" + s);
                        break;
                    case R.layout.listitem:
                        // do something by yourseft

                        break;

                }

            }
        };
    }

    private void makeSinpleAdapter() {
        simpleAdapter = new CommonAdapter<String>(this, R.layout.listitem_text, MakeLovelyData.makeDatas()) {
            @Override
            public void convert(ViewHolder holder, String o) {
                holder.setText(R.id.text1, "" + o);
                holder.setTextSize(R.id.text1, 15);

            }
        };
    }

    @Override
    public void onSpinnerItemClick(int position) {
        mRecyclerView.setVisibility(position==0? View.VISIBLE:View.GONE);
        mRecyclerView2.setVisibility(position==1? View.VISIBLE:View.GONE);
    }

    @Override
    public List<String> getSpinnerData() {
        return Arrays.asList(getResources().getStringArray(R.array.adapters));
    }

}
