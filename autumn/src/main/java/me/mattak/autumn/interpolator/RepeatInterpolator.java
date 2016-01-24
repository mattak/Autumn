package me.mattak.autumn.interpolator;

import android.view.animation.Animation;
import android.view.animation.Interpolator;

/**
 * RepeatInterpolator
 * Created by mattak on 2016/01/23.
 */
public class RepeatInterpolator implements Interpolator {
    public final static int RESTART = Animation.RESTART;
    public final static int REVERSE = Animation.REVERSE;

    private final Interpolator mInterpolator;
    private final float mScale;

    public RepeatInterpolator(Interpolator interpolator, int repeatCount) {
        this.mInterpolator = interpolator;
        this.mScale = repeatCount + 1;
    }

    @Override
    public float getInterpolation(float input) {
        double scaledInput = input * mScale;
        float newInput = (float) (scaledInput - Math.ceil(scaledInput));
        return mInterpolator.getInterpolation(newInput);
    }
}