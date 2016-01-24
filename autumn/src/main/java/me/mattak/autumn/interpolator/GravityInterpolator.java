package me.mattak.autumn.interpolator;

import android.view.animation.Interpolator;

/**
 * GravityInterpolator
 * Created by mattak on 2016/01/24.
 */
public class GravityInterpolator implements Interpolator {
    private final float mFactor;

    public GravityInterpolator() {
        this(1.0f);
    }

    public GravityInterpolator(float factor) {
        mFactor = factor;
    }

    @Override
    public float getInterpolation(float input) {
        final float x = input * 2;
        final float nx = x - 1.0f;
        return 1.0f - mFactor * nx * nx;
    }
}
