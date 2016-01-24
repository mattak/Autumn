package me.mattak.autumn;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * InterpolatorPreset
 * Created by mattak on 2016/01/24.
 */
public enum InterpolatorPreset {
    Linear {
        public Interpolator create(float force) {
            return new LinearInterpolator();
        }
    },;

    abstract public Interpolator create(float force);
}