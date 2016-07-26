package com.demotest.testapp;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marshon.adapters.AnimationAdapter;


/**
 * //自定义动画管理类
 * Created by Administrator on 2016/4/25.
 */
public class ChooseAnimatorsAdapter extends AnimationAdapter {

    public static final int ANIMATORFLAG_ALPHA = 1;
    public static final int ANIMATORFLAG_TRANSLATION = 3;
    public static final int ANIMATORFLAG_SCALE = 2;

    private PropertyValuesHolder[] animators;
    private int flag = 2;


    public ChooseAnimatorsAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter) {
        super(adapter);
    }

    @Override
    protected Animator[] getAnimators(View view) {
        if (animators != null) {
            ObjectAnimator valueAnimator = ObjectAnimator.ofPropertyValuesHolder(view,animators);
            return new Animator[]{valueAnimator};
        } else {
            return makeAnimator(view);

        }
    }

    private Animator[] makeAnimator(final View view) {
        switch (flag) {
            case 1:
                return new Animator[]{ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1f)};
            case 2:
                return new Animator[] { ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1f),ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 1f),ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 1f) };
            // ....
            case 3:
                return new Animator[] {ObjectAnimator.ofFloat(view, "translationY", view.getMeasuredHeight(), 0) };
            case 4:
                return new Animator[] {ObjectAnimator.ofFloat(view, "translationX", view.getRootView().getWidth(), 0) };

        }
        return null;
    }


    public void setAnimator(PropertyValuesHolder[] animators) {
        this.animators = animators;
    }

    public void setAnimatorFlag(int flag) {
        this.flag = flag;
    }
}
