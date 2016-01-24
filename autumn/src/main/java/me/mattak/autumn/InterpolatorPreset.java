package me.mattak.autumn;

import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * InterpolatorPreset
 * Created by mattak on 2016/01/24.
 */
public enum InterpolatorPreset {
    EaseIn {
        public Interpolator create(float force) {
            return new AccelerateInterpolator();
        }
    },
    EaseOut {
        public Interpolator create(float force) {
            return new DecelerateInterpolator();
        }
    },
    EaseInOut {
        public Interpolator create(float force) {
            return new AccelerateDecelerateInterpolator();
        }
    },
    Linear {
        public Interpolator create(float force) {
            return new LinearInterpolator();
        }
    },
    Spring {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.5f, 1.1f + force / 3, 1f, 1f);
        }
    },
    EaseInSine {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.47f, 0f, 0.745f, 0.715f);
        }
    },
    EaseOutSine {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.39f, 0.575f, 0.565f, 1f);
        }
    },
    EaseInOutSine {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.445f, 0.05f, 0.55f, 0.95f);
        }
    },
    EaseInQuad {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.55f, 0.085f, 0.68f, 0.53f);
        }
    },
    EaseOutQuad {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.25f, 0.46f, 0.45f, 0.94f);
        }
    },
    EaseInOutQuad {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.455f, 0.03f, 0.515f, 0.955f);
        }
    },
    EaseInCubic {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.55f, 0.055f, 0.675f, 0.19f);
        }
    },
    EaseOutCubic {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.215f, 0.61f, 0.355f, 1f);
        }
    },
    EaseInOutCubic {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.645f, 0.045f, 0.355f, 1f);
        }
    },
    EaseInQuart {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.895f, 0.03f, 0.685f, 0.22f);
        }
    },
    EaseOutQuart {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.165f, 0.84f, 0.44f, 1f);
        }
    },
    EaseInOutQuart {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.77f, 0.0f, 0.175f, 1f);
        }
    },
    EaseInQuint {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.755f, 0.05f, 0.855f, 0.06f);
        }
    },
    EaseOutQuint {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.23f, 1f, 0.32f, 1f);
        }
    },
    EaseInOutQuint {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.86f, 0f, 0.07f, 1f);
        }
    },
    EaseInExpo {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.95f, 0.05f, 0.795f, 0.035f);
        }
    },
    EaseOutExpo {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.19f, 1f, 0.22f, 1f);
        }
    },
    EaseInOutExpo {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(1f, 0f, 0f, 1f);
        }
    },
    EaseInCirc {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.6f, 0.04f, 0.98f, 0.335f);
        }
    },
    EaseOutCirc {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.075f, 0.82f, 0.165f, 1f);
        }
    },
    EaseInOutCirc {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.785f, 0.135f, 0.15f, 0.86f);
        }
    },
    EaseInBack {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.6f, -0.28f, 0.735f, 0.045f);
        }
    },
    EaseOutBack {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.175f, 0.885f, 0.32f, 1.275f);
        }
    },
    EaseInOutBack {
        public Interpolator create(float force) {
            return PathInterpolatorCompat.create(0.68f, -0.55f, 0.265f, 1.55f);
        }
    },;

    abstract public Interpolator create(float force);
}