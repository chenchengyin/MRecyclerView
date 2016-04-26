package com.demotest.testapp;

/**
 * Created by Administrator on 2016/4/25.
 */

import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.Arrays;
import java.util.List;

public class MarshonAnimatorActivity extends BaseActivity {


    private RecyclerView.Adapter adapter;
    private ChooseAnimatorsAdapter animAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marsreview);


        //do somethings about adapter;
        adapter = new CommonAdapter<String>(this, R.layout.listitem, MakeLovelyData.makeDatas()) {
            @Override
            public void convert(final ViewHolder holder, String s) {
                //do somethings;
            }
        };

        //set up recyclerview
        final LinearLayoutManager manager=new LinearLayoutManager(this);

        animAdapter =new ChooseAnimatorsAdapter(adapter);
        animAdapter.setAnimatorFlag(ChooseAnimatorsAdapter.ANIMATORFLAG_SCALE);
        mRecyclerView.setAdapter(animAdapter);
        mRecyclerView.setLayoutManager(manager);
    }


    @Override
    public List<String> getSpinnerData() {
        return Arrays.asList(getResources().getStringArray(R.array.animators));
    }

    @Override
    public void onSpinnerItemClick(int position) {
        //这里体现了两种实现属性动画的方式
        // 1.ValueAnimator
        // 2.ObjectAnimator
        switch (position) {
            case 0:
                animAdapter.setAnimatorFlag(ChooseAnimatorsAdapter.ANIMATORFLAG_ALPHA);

                break;
            case 1:
                animAdapter.setAnimatorFlag(ChooseAnimatorsAdapter.ANIMATORFLAG_SCALE);
                break;
            case 2:
                animAdapter.setAnimatorFlag(ChooseAnimatorsAdapter.ANIMATORFLAG_TRANSLATION);
                break;

            case 3:
                PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.1f,1.0f);
                animAdapter.setAnimator(new PropertyValuesHolder[]{alpha});
                break;
            case 4:
                PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.1f,1.0f);
                PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.1f,1.0f);
                animAdapter.setAnimator(new PropertyValuesHolder[]{scaleX,scaleY});
                break;
            case 5:
                PropertyValuesHolder translationY = PropertyValuesHolder.ofFloat("translationY",400,0);
                animAdapter.setAnimator(new PropertyValuesHolder[]{translationY});
                break;
            case 6:
                PropertyValuesHolder translationX = PropertyValuesHolder.ofFloat("translationX",mRecyclerView.getMeasuredWidth()-20,0);
                animAdapter.setAnimator(new PropertyValuesHolder[]{translationX});
                break;
        }


    }
}
