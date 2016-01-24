package me.mattak.autumn.animator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Interpolator;

import me.mattak.autumn.interpolator.CompositeInterpolator;
import me.mattak.autumn.interpolator.GravityInterpolator;

/**
 * JumpAnimator
 * Created by mattak on 2016/01/20.
 */
public class JumpAnimator extends BaseAnimator {
    public JumpAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public JumpAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public JumpAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    public Animator create() {
        float move = -this.force * this.view.getHeight();
        final Interpolator gravityInterpolator = new GravityInterpolator();

        ValueAnimator half = ValueAnimator.ofFloat(0f, move);
        half.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                float newValue = gravityInterpolator.getInterpolation(value);
                view.setTranslationY(newValue);
            }
        });
        half.setInterpolator(new CompositeInterpolator(gravityInterpolator, this.interpolator));
        half.setDuration(this.duration);
        half.setStartDelay(this.delay);
        half.addListener(this.endListener);

        return half;
    }

    public void start() {
        final float move = -this.force * this.view.getHeight();
        final Interpolator gravityInterpolator = new GravityInterpolator();

        ViewCompat
                .animate(this.view)
                .translationY(move)
                .setStartDelay(this.delay)
                .setDuration(this.duration)
                .setListener(this.endListener)
                .setInterpolator(new CompositeInterpolator(gravityInterpolator, this.interpolator));
    }
}