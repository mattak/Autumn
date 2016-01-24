package me.mattak.autumn.animator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * SwingAnimator
 * Created by mattak on 2016/01/24.
 */
public class SwingAnimator extends BaseAnimator {
    public SwingAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public SwingAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public SwingAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    public Animator create() {
        ValueAnimator animator = ValueAnimator.ofFloat(
                0,
                30 * this.force,
                -30 * this.force,
                30 * this.force,
                0
        );
        animator.setDuration(this.duration);
        animator.setInterpolator(this.interpolator);
        animator.setStartDelay(this.delay);
        animator.addListener(this.endListener);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float)animation.getAnimatedValue();
                view.setRotation(value);
            }
        });

        return animator;
    }
}