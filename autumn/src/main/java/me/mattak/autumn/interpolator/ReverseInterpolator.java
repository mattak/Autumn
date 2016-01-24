package me.mattak.autumn.interpolator;

import android.view.animation.Interpolator;

/**
 * ReverseInterpolator
 * Created by mattak on 2016/01/23.
 */
public class ReverseInterpolator implements Interpolator {
    private final Interpolator mInterpolator;

    public ReverseInterpolator(final Interpolator originalInterpolator) {
        mInterpolator = originalInterpolator;
    }

    @Override
    public float getInterpolation(final float input) {
        return mInterpolator.getInterpolation(1.0f - input);
    }
}
