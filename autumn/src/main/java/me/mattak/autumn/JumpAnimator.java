package me.mattak.autumn;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * JumpAnimator
 * Created by mattak on 2016/01/20.
 */
public class JumpAnimator implements AnimatorFactory {
    private final View view;
    private final float force;
    private final long duration;
    private final long delay;
    private final Interpolator interpolator;

    public JumpAnimator(
            View view,
            float force,
            long duration,
            long delay,
            Interpolator interpolator
    ) {
        this.view = view;
        this.force = force;
        this.duration = duration;
        this.delay = delay;
        this.interpolator = interpolator;
    }

    @Override
    public Animator create() {
        float top = this.force * this.view.getHeight() / 2;

        ObjectAnimator animator = ObjectAnimator.ofFloat(this.view, "translateY", 0f, top);
        animator.setStartDelay(this.delay);
        animator.setInterpolator(this.interpolator);
        animator.setDuration(this.duration / 2);
        animator.setRepeatCount(1);
        animator.setRepeatCount(ValueAnimator.REVERSE);

        return animator;
    }
}