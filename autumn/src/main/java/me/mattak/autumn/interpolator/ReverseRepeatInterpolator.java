package me.mattak.autumn.interpolator;

import android.view.animation.Interpolator;

/**
 * ReverseRepeatInterpolator
 * Created by mattak on 2016/01/23.
 */
public class ReverseRepeatInterpolator implements Interpolator {
    private final Interpolator mInterpolator;
    private final float mScale;

    public ReverseRepeatInterpolator(Interpolator interpolator, int repeatCount) {
        this.mInterpolator = interpolator;
        this.mScale = repeatCount + 1;
    }

    @Override
    public float getInterpolation(float input) {
        double scaledInput = input * mScale;
        int count = (int) scaledInput % 2;

        if (count % 2 == 1) {
            float newInput = (float) (Math.floor(scaledInput) - scaledInput);
            return mInterpolator.getInterpolation(newInput);
        } else {
            float newInput = (float) (scaledInput - Math.ceil(scaledInput));
            return mInterpolator.getInterpolation(newInput);
        }
    }
}
