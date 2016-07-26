package com.demotest.testapp;

import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.marshon.mrecyclerview.MRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public abstract class BaseActivity extends AppCompatActivity implements MRecyclerView.LoadingListener {

    protected Toolbar toolBar;
    protected MRecyclerView mRecyclerView;
    protected Spinner spinner;

    @Override
    public void setContentView(@LayoutRes int laoutResID) {
        View rootView = View.inflate(this, laoutResID, null);
        init(rootView);
        super.setContentView(rootView);

    }

    private void init(View rootView) {
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        mRecyclerView = (MRecyclerView) rootView.findViewById(R.id.mRecyclerView);
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
//        spinner.setAdapter(adapter);

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
