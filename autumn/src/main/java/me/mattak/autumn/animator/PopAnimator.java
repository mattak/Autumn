package me.mattak.autumn.animator;

import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.Interpolator;

/**
 * PopAnimator
 * Created by mattak on 2016/01/20.
 */
public class PopAnimator extends ScaleAnimator {
    public PopAnimator(View view, float force, long duration, long delay) {
        super(view, force, duration, delay);
    }

    public PopAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        super(view, force, duration, delay, interpolator);
    }

    public PopAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        super(view, force, duration, delay, interpolator, endListener);
    }

    @Override
    protected PropertyValuesHolder createScaleXHolder(float factor) {
        return PropertyValuesHolder.ofFloat("scaleX",
                1.0f,
                1.0f + 0.2f * factor,
                1.0f - 0.2f * factor,
                1.0f + 0.2f * factor,
                1.0f
        );
    }

    @Override
    protected PropertyValuesHolder createScaleYHolder(float factor) {
        return PropertyValuesHolder.ofFloat("scaleY",
                1.0f,
                1.0f + 0.2f * factor,
                1.0f - 0.2f * factor,
                1.0f + 0.2f * factor,
                1.0f
        );
    }
}