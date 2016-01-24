package me.mattak.autumn.animator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * FadeInAnimator
 * Created by mattak on 2016/01/24.
 */
public class FadeInAnimator extends BaseAnimator {
    public FadeInAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public FadeInAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public FadeInAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    public Animator create() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);

        animator.setInterpolator(this.interpolator);
        animator.setDuration(this.duration);
        animator.setStartDelay(this.delay);
        animator.addListener(this.endListener);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                view.setAlpha(value);
            }
        });

        return animator;
    }

    public void start() {
        this.view
                .animate()
                .alpha(1.0f)
                .setListener(this.endListener)
                .setInterpolator(this.interpolator)
                .setDuration(this.duration)
                .setStartDelay(this.delay);
    }
}