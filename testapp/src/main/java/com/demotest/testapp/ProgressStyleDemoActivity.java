package com.demotest.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.jcodecraeer.xrecyclerview.progressindicator.AVLoadingIndicatorView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.Arrays;
import java.util.List;

public class ProgressStyleDemoActivity extends BaseActivity  {

    private int[] progressstyles=new int[]{
            AVLoadingIndicatorView.BallBeat,
            AVLoadingIndicatorView.BallClipRotate,
            AVLoadingIndicatorView.BallGridBeat,
            AVLoadingIndicatorView.BallPulse,
            AVLoadingIndicatorView.BallRotate,
            AVLoadingIndicatorView.BallSpinFadeLoader,
            AVLoadingIndicatorView.BallTrianglePath,
            AVLoadingIndicatorView.BallZigZagDeflect,
            AVLoadingIndicatorView.CubeTransition,
            AVLoadingIndicatorView.LineScaleParty,
            AVLoadingIndicatorView.Pacman,
            AVLoadingIndicatorView.SemiCircleSpin,
            AVLoadingIndicatorView.SquareSpin,
            AVLoadingIndicatorView.TriangleSkewSpin
    };
    private static List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marsreview);
        datas = MakeLovelyData.makeDatas();
        CommonAdapter<String> adapter = new CommonAdapter<String>(this,R.layout.listitem,datas) {
            @Override
            public void convert(final ViewHolder holder, String o) {

                final SwipeLayout swipeLayout= (SwipeLayout) holder.getConvertView().findViewById(R.id.swipelayout);
                TextView tv_delete= (TextView) swipeLayout.findViewById(R.id.tv_delete);

                swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
                swipeLayout.addDrag(SwipeLayout.DragEdge.Right, tv_delete);

                tv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        swipeLayout.close(true);
                        notifyItemRemoved(holder.getLayoutPosition());
                    }
                });
            }
        };

        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onSpinnerItemClick(int position) {
        mRecyclerView.setLoadingMoreProgressStyle(progressstyles[position]);
        mRecyclerView.setRefreshProgressStyle(progressstyles[position]);
    }

    @Override
    public List<String> getSpinnerData() {
        return Arrays.asList(getResources().getStringArray(R.array.progressstyles));
    }
}
