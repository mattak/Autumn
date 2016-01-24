package me.mattak.autumn.interpolator;

import android.view.animation.Interpolator;

/**
 * CompositeInterpolator
 * Created by mattak on 2016/01/24.
 */
public class CompositeInterpolator implements Interpolator {
    private Interpolator f1;
    private Interpolator f2;

    public CompositeInterpolator(Interpolator f1, Interpolator f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public float getInterpolation(float input) {
        return f1.getInterpolation(f2.getInterpolation(input));
    }
}
