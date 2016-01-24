package me.mattak.autumn;

import android.view.View;
import android.view.animation.Interpolator;

import me.mattak.autumn.animator.FadeInAnimator;
import me.mattak.autumn.animator.FadeOutAnimator;
import me.mattak.autumn.animator.JumpAnimator;
import me.mattak.autumn.animator.MorphAnimator;
import me.mattak.autumn.animator.PopAnimator;
import me.mattak.autumn.animator.ShakeAnimator;
import me.mattak.autumn.animator.SqueezeAnimator;
import me.mattak.autumn.animator.SwingAnimator;
import me.mattak.autumn.animator.WobbleAnimator;

/**
 * AnimatorPreset
 * Created by mattak on 2016/01/24.
 */
public enum AnimatorPreset {
    FadeIn {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new FadeInAnimator(view, force, duration, delay, interpolator);
        }
    },
    FadeOut {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new FadeOutAnimator(view, force, duration, delay, interpolator);
        }
    },
    Jump {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new JumpAnimator(view, force, duration, delay, interpolator);
        }
    },
    Morph {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new MorphAnimator(view, force, duration, delay, interpolator);
        }
    },
    Pop {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new PopAnimator(view, force, duration, delay, interpolator);
        }
    },
    Shake {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new ShakeAnimator(view, force, duration, delay, interpolator);
        }
    },
    Squeeze {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new SqueezeAnimator(view, force, duration, delay, interpolator);
        }
    },
    Swing {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new SwingAnimator(view, force, duration, delay, interpolator);
        }
    },
    Wobble {
        @Override
        public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator) {
            return new WobbleAnimator(view, force, duration, delay, interpolator);
        }
    },;

    abstract public AnimatorFactory createAnimator(View view, float force, long duration, long delay, Interpolator interpolator);
}