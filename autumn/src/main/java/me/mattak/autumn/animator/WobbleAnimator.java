package me.mattak.autumn.animator;

import android.animation.Animator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * WobbleAnimator
 * Created by mattak on 2016/01/24.
 */
public class WobbleAnimator extends BaseAnimator {
    public WobbleAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public WobbleAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public WobbleAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    public Animator create() {
        PropertyValuesHolder holderRotation = PropertyValuesHolder.ofFloat("rotation",
                0,
                30f * this.force,
                -30f * this.force,
                30f * this.force,
                0
        );
        PropertyValuesHolder holderPositionX = PropertyValuesHolder.ofFloat("x",
                0,
                this.view.getWidth() * 0.3f * this.force,
                this.view.getWidth() * -0.3f * this.force,
                this.view.getWidth() * 0.3f * this.force,
                0
        );
        ValueAnimator animator = ValueAnimator.ofPropertyValuesHolder(holderRotation, holderPositionX);
        animator.setDuration(this.duration);
        animator.setStartDelay(this.delay);
        animator.setInterpolator(this.interpolator);
        animator.addListener(this.endListener);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float r = (Float) animation.getAnimatedValue("rotation");
                float x = (Float) animation.getAnimatedValue("x");
                view.setRotation(r);
                view.setTranslationX(x);
            }
        });

        return animator;
    }
}