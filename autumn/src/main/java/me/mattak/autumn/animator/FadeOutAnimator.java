package me.mattak.autumn.animator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * FadeOutAnimator
 * Created by mattak on 2016/01/24.
 */
public class FadeOutAnimator extends BaseAnimator {
    public FadeOutAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public FadeOutAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public FadeOutAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    public Animator create() {
        ValueAnimator animator = ValueAnimator.ofFloat(1.0f, 0.0f);

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
                .alpha(0.0f)
                .setListener(this.endListener)
                .setInterpolator(this.interpolator)
                .setDuration(this.duration)
                .setStartDelay(this.delay);
    }
}
