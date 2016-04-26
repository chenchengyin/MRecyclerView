package com.demotest.testapp;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.base.adapter.ViewHolder;
 import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public abstract class BaseActivity extends AppCompatActivity implements XRecyclerView.LoadingListener {

    protected Toolbar toolBar;
    protected XRecyclerView mRecyclerView;
    protected Spinner spinner;

    @Override
    public void setContentView(@LayoutRes int laoutResID) {
        View rootView = View.inflate(this, laoutResID, null);
        init(rootView);
        super.setContentView(rootView);

    }

    private void init(View rootView) {
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        mRecyclerView = (XRecyclerView) rootView.findViewById(R.id.mRecyclerView);
        toolBar = (Toolbar) rootView.findViewById(R.id.tool_bar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Marshon");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLoadingListener(this);
        setUpSpinner();
    }

    private void setUpSpinner() {
//        final List<String> stringArray = Arrays.asList(getResources().getStringArray(R.array.progressstyles));
        List<String> spinnerData = getSpinnerData();
        if(spinnerData==null){
            return;
        }
        com.zhy.base.adapter.abslistview.CommonAdapter<String> adapter = new com.zhy.base.adapter.abslistview.CommonAdapter<String>(this, R.layout.listitem_text,spinnerData ) {
            @Override
            public void convert(ViewHolder holder, String o) {
                holder.setText(R.id.text1, "" + o);
                holder.setTextSize(R.id.text1, 15);

            }
        };
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                onSpinnerItemClick(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onRefresh() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.getAdapter().notifyDataSetChanged();
                mRecyclerView.refreshComplete();
            }
        }, 3000);
    }

    @Override
    public void onLoadMore() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = MakeLovelyData.makeDatas();
                mRecyclerView.getAdapter().notifyDataSetChanged();
                mRecyclerView.loadMoreComplete();
            }
        }, 3000);
    }

    public abstract List<String> getSpinnerData();

    public abstract void onSpinnerItemClick(int position);

}
