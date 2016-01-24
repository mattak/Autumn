package me.mattak.autumn.animator;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * ScaleAnimator
 * Created by mattak on 2016/01/24.
 */
public abstract class ScaleAnimator extends BaseAnimator {
    public ScaleAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public ScaleAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public ScaleAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    public Animator create() {
        PropertyValuesHolder holderX = this.createScaleXHolder(this.force);
        PropertyValuesHolder holderY = this.createScaleYHolder(this.force);
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(holderX, holderY);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scaleX = (Float) animation.getAnimatedValue("scaleX");
                float scaleY = (Float) animation.getAnimatedValue("scaleY");
                view.setScaleX(scaleX);
                view.setScaleY(scaleY);
            }
        });
        animator.setDuration(this.duration);
        animator.setInterpolator(this.interpolator);
        animator.setStartDelay(this.delay);
        animator.addListener(this.endListener);
        return animator;
    }

    abstract protected PropertyValuesHolder createScaleXHolder(float factor);

    abstract protected PropertyValuesHolder createScaleYHolder(float factor);
}