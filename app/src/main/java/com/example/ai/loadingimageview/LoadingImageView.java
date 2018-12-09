package com.example.ai.loadingimageview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

/**
 * setTop方法不会让onLayout方法调用
 */
public class LoadingImageView extends ImageView {
    // 图片顶部纵坐标
    private int mTop;
    // 图片当前的索引
    private int mCurrentIndex = 0;
    // 图片的数量
    private static int mImgCount = 3;

    public LoadingImageView(Context context) {
        this(context,null);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0,100,0);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(2000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer dx = (Integer)animation.getAnimatedValue();
                Log.d("TAG", "onAnimationUpdate: dx = "+dx);
                Log.d("TAG", "onAnimationUpdate: mTop = "+mTop);
                setTop(mTop-dx);// 单位是px
                Log.d("TAG", "onAnimationUpdate: mTop-dx="+getTop());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationRepeat(Animator animation) {
                mCurrentIndex++;
                switch (mCurrentIndex % mImgCount){
                    case 0:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_1));
                        break;
                    case 1:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_2));
                        break;
                    case 2:
                        setImageDrawable(getResources().getDrawable(R.drawable.pic_3));
                        break;
                }

            }
            @Override
            public void onAnimationStart(Animator animation) {
                setImageDrawable(getResources().getDrawable(R.drawable.pic_1));
            }

        });

        valueAnimator.start();

    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("TAG", "onLayout: changed = "+changed);
        mTop = top;
        Log.d("TAG", "onLayout: top = "+top);
        Log.d("TAG", "onLayout: getTop() = "+getTop());
    }



}
