package me.mattak.autumn.animator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * ShakeAnimator
 * Created by mattak on 2016/01/24.
 */
public class ShakeAnimator extends BaseAnimator {
    public ShakeAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public ShakeAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public ShakeAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    public Animator create() {
        ValueAnimator animator = ValueAnimator.ofFloat(
                0f,
                this.view.getWidth() * 0.3f * this.force,
                this.view.getWidth() * -0.3f * this.force,
                this.view.getWidth() * 0.3f * this.force,
                0f
        );
        animator.setStartDelay(this.delay);
        animator.setDuration(this.duration);
        animator.setInterpolator(this.interpolator);
        animator.addListener(this.endListener);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                view.setTranslationX(value);
            }
        });
        return animator;
    }
}