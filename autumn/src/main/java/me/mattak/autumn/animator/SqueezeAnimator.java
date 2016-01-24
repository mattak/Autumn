package me.mattak.autumn.animator;

import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * SqueezeAnimator
 * Created by mattak on 2016/01/24.
 */
public class SqueezeAnimator extends ScaleAnimator {
    public SqueezeAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public SqueezeAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public SqueezeAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    protected PropertyValuesHolder createScaleXHolder(float force) {
        return PropertyValuesHolder.ofFloat("scaleX",
                1.0f,
                1.5f * force,
                0.5f,
                1.5f * force,
                1.0f
        );
    }

    @Override
    protected PropertyValuesHolder createScaleYHolder(float force) {
        return PropertyValuesHolder.ofFloat("scaleY",
                1.0f,
                0.5f,
                1.0f,
                0.5f,
                1.0f
        );
    }
}