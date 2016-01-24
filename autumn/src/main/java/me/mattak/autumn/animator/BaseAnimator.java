package me.mattak.autumn.animator;

import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import me.mattak.autumn.AnimatorFactory;

/**
 * BaseAnimator
 * Created by mattak on 2016/01/24.
 */
public abstract class BaseAnimator implements AnimatorFactory {
    protected final View view;
    protected final float force;
    protected final long duration;
    protected final long delay;
    protected final Interpolator interpolator;
    protected final AnimatorOnEndListener endListener;

    public BaseAnimator(View view, float force, long duration, long delay) {
        this(view, force, duration, delay, new LinearInterpolator());
    }

    public BaseAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
        this(view, force, duration, delay, interpolator, null);
    }

    public BaseAnimator(View view, float force, long duration, long delay, Interpolator interpolator, Runnable endListener) {
        this.view = view;
        this.force = force;
        this.duration = duration;
        this.delay = delay;
        this.interpolator = interpolator;
        this.endListener = new AnimatorOnEndListener(endListener);
    }
}