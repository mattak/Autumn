package me.mattak.autumn.animator;

import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * MorphAnimator
 * Created by mattak on 2016/01/20.
 */
public class MorphAnimator extends ScaleAnimator {
    public MorphAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public MorphAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public MorphAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    protected PropertyValuesHolder createScaleXHolder(float factor) {
        return PropertyValuesHolder.ofFloat("scaleX",
                1.0f,
                1.3f * factor,
                0.7f,
                1.3f * factor,
                1.0f
        );
    }

    @Override
    protected PropertyValuesHolder createScaleYHolder(float factor) {
        return PropertyValuesHolder.ofFloat("scaleY",
                1.0f,
                0.7f,
                1.3f * factor,
                0.7f,
                1.0f
        );
    }
}